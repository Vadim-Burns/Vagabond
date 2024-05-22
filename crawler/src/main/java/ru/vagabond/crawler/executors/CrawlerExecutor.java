package ru.vagabond.crawler.executors;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import ru.vagabond.crawler.core.executor.AbstractMultiThreadExecutor;
import ru.vagabond.crawler.core.executor.Executor;
import ru.vagabond.crawler.core.extension.ListExtension;
import ru.vagabond.crawler.http.VagabondHttpClient;
import ru.vagabond.crawler.http.VagabondHttpClientFactory;
import ru.vagabond.crawler.infra.environment.EnvironmentService;
import ru.vagabond.crawler.properties.CrawlerProperties;
import ru.vagabond.crawler.services.TelegraphUrlsService;
import ru.vagabond.crawler.services.TransactionalExecutionService;
import ru.vagabond.crawler.services.UriService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Executor который ищет рабочие ссылки в telegra.ph
 */
@Slf4j
@Executor
@ExtensionMethod(ListExtension.class)
@RequiredArgsConstructor
public class CrawlerExecutor extends AbstractMultiThreadExecutor {

    private final static int SLEEP_TIME_DEFAULT = 1000 * 60;
    private final static String SLEEP_TIME_ENV = "crawler.sleep-time";
    private final static int RETRY_COUNT_DEFAULT = 2;
    private final static String RETRY_COUNT_ENV = "crawler.retry-count";

    // CrawlerThreadPool
    private final static String THREAD_NAME_PREFIX = "CrawlerTP-";
    private final static String TELEGRAM_PREFIX = "https://telegra.ph/";

    private final UriService uriService;
    private final TelegraphUrlsService urlsService;
    private final TransactionalExecutionService transactionalExecutionService;
    private final EnvironmentService environmentService;
    private final CrawlerProperties crawlerProperties;

    private final VagabondHttpClientFactory httpClientFactory;
    private VagabondHttpClient httpClient;
    private Supplier<Integer> sleepTimeSupplier;
    private Supplier<Integer> retryCountSupplier;

    @PostConstruct
    private void postConstruct() {
        log.info("Init suppliers");
        sleepTimeSupplier = Suppliers.memoizeWithExpiration(
                () -> this.environmentService.getValueInt(SLEEP_TIME_ENV).orElse(SLEEP_TIME_DEFAULT),
                5,
                TimeUnit.MINUTES
        );
        retryCountSupplier = Suppliers.memoizeWithExpiration(
                () -> this.environmentService.getValueInt(RETRY_COUNT_ENV).orElse(RETRY_COUNT_DEFAULT),
                5,
                TimeUnit.MINUTES
        );
        log.info("Init http client");
        httpClient = httpClientFactory.getClient();
    }

    @Override
    protected void executeSingleThread() {
        while (true) {
            try {
                checkUri();
            } catch (InterruptedException ex) {
                log.info("Interrupting crawler thread");
                break;
            } catch (Exception ex) {
                log.error("Thread died to exception", ex);
            }
        }
    }

    private void checkUri() throws InterruptedException {
        Optional<String> uri = uriService.selectUriForCheck();
        // На случай, если нечего проверять
        if (uri.isEmpty()) {
            log.info("No uri for check");
            Thread.sleep(sleepTimeSupplier.get());
            return;
        }

        log.info("Selected uri is {}", uri.get());

        Set<String> foundUrls = new HashSet<>();
        for (int month = 1; month < 13; month++) {
            for (int day = 1; day < 32; day++) {
                int initTry = 1;
                while (processDayManyTimes(uri.get(), month, day, initTry, foundUrls)) {
                    initTry += retryCountSupplier.get();
                }
            }
        }

        transactionalExecutionService.addCallableToExecute(() -> {
            log.info("Found {} urls on uri {}", foundUrls.size(), uri.get());

            urlsService.saveFoundUrls(foundUrls);
            uriService.updateProcessedUri(uri.get());

            return null;
        });
    }

    /**
     * В один день могут несколько записей с одинаковым именем.
     *
     * @return true если хотя бы одна запись найдена
     */
    private boolean processDayManyTimes(String uri, int month, int day, int initTry, Set<String> foundUrls) throws InterruptedException {
        String dayUrl = TELEGRAM_PREFIX + uri + "-" + intTo2charString(month) + "-" + intTo2charString(day);
        boolean isFound = false;

        for (int i = initTry; i < initTry + retryCountSupplier.get(); i++) {
            if (i == 1) {
                isFound = processUrl(dayUrl, foundUrls) || isFound;
            } else {
                isFound = processUrl(dayUrl + "-" + i, foundUrls) || isFound;
            }
        }

        return isFound;
    }

    private boolean processUrl(String url, Set<String> foundUrls) throws InterruptedException {
        int statusCode = httpClient.headUrl(url);
        while (true) {
            if (statusCode == 200) {
                foundUrls.add(url);
                return true;
            } else if (statusCode == 404) {
                return false;
            }
        }
    }

    private String intTo2charString(int value) {
        return value / 10 > 0 ? String.valueOf(value) : "0" + value;
    }

    @Override
    protected int getThreadCount() {
        return crawlerProperties.getThreadCount();
    }


    @Override
    protected String getThreadNamePrefix() {
        return THREAD_NAME_PREFIX;
    }
}
