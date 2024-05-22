package ru.vagabond.crawler.executors;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.vagabond.crawler.core.executor.AbstractMultiThreadExecutor;
import ru.vagabond.crawler.core.executor.Executor;
import ru.vagabond.crawler.http.VagabondHttpClient;
import ru.vagabond.crawler.http.VagabondHttpClientFactory;
import ru.vagabond.crawler.http.VagabondHttpResponse;
import ru.vagabond.crawler.infra.environment.EnvironmentService;
import ru.vagabond.crawler.models.*;
import ru.vagabond.crawler.properties.IndexerProperties;
import ru.vagabond.crawler.services.ExternalUrlsService;
import ru.vagabond.crawler.services.LabelService;
import ru.vagabond.crawler.services.TelegraphUrlsService;
import ru.vagabond.crawler.services.TransactionalExecutionService;
import ru.vagabond.crawler.utils.Analyzer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static ru.vagabond.crawler.utils.Constants.TELEGRAPH_PREFIX;

@Slf4j
@Executor
@RequiredArgsConstructor
public class IndexerExecutor extends AbstractMultiThreadExecutor {

    // Сколько url-ов мы за один раз проверяем
    private static final String URL_AT_ONCE_ENV = "index.url-at-once";
    private static final Integer URL_AT_ONCE_DEFAULT = 10;
    // Сколько спим, если нечего проверять
    private final static int SLEEP_TIME_DEFAULT = 1000 * 60;
    private final static String SLEEP_TIME_ENV = "indexer.sleep-time";

    // IndexerThreadPool
    private final static String THREAD_NAME_PREFIX = "IndexerTP-";

    private final IndexerProperties indexerProperties;
    private final EnvironmentService environmentService;

    private final TelegraphUrlsService telegraphUrlsService;
    private final ExternalUrlsService externalUrlsService;
    private final LabelService labelService;
    private final VagabondHttpClientFactory httpClientFactory;
    private final TransactionalExecutionService transactionalExecutionService;

    private VagabondHttpClient httpClient;
    private Supplier<Integer> urlAnalyzeAtOnce;
    private Supplier<Integer> sleepTimeSupplier;

    @PostConstruct
    private void postConstruct() {
        log.info("Init suppliers");
        urlAnalyzeAtOnce = Suppliers.memoizeWithExpiration(
                () -> environmentService.getValueInt(URL_AT_ONCE_ENV).orElse(URL_AT_ONCE_DEFAULT),
                5,
                TimeUnit.MINUTES
        );
        sleepTimeSupplier = Suppliers.memoizeWithExpiration(
                () -> this.environmentService.getValueInt(SLEEP_TIME_ENV).orElse(SLEEP_TIME_DEFAULT),
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
                analyzeUrls();
            } catch (InterruptedException e) {
                log.info("Interrupting indexer thread");
                break;
            } catch (Exception ex) {
                log.error("Thread died to exception", ex);
            }
        }
    }

    private void analyzeUrls() throws InterruptedException {
        List<UrlToAnalyze> urlsToAnalyze = telegraphUrlsService.selectUrlsToAnalyze(urlAnalyzeAtOnce.get());
        log.info("Got {} urls to analyze", urlsToAnalyze.size());

        // На случай, если нечего проверять
        if (urlsToAnalyze.isEmpty()) {
            Thread.sleep(sleepTimeSupplier.get());
            return;
        }

        Set<ExternalUrl> externalUrls = new HashSet<>();
        Set<Label> labels = new HashSet<>();
        Set<TelegraphUrl> telegraphUrls = new HashSet<>();
        Set<UrlAnalyzed> urlAnalyzedSet = new HashSet<>();

        for (UrlToAnalyze url : urlsToAnalyze) {
            log.info("Analyze url {}", url);
            try {
                analyzeUrlResponse(url, httpClient.getUrl(url.url()), externalUrls, labels, telegraphUrls, urlAnalyzedSet);
            } catch (InterruptedException ex) {
                throw ex;
            } catch (RuntimeException ex) {
                log.error("Exception on url {}", url, ex);
            }
        }

        transactionalExecutionService.addCallableToExecute(() -> {
            telegraphUrlsService.saveAnalyzedUrls(urlAnalyzedSet, telegraphUrls);
            labelService.saveLabels(labels);
            externalUrlsService.saveExternalUrls(externalUrls);

            return null;
        });
    }

    private void analyzeUrlResponse(UrlToAnalyze url, VagabondHttpResponse response, Set<ExternalUrl> externalUrls, Set<Label> labels, Set<TelegraphUrl> telegraphUrls, Set<UrlAnalyzed> urlAnalyzedSet) throws InterruptedException {
        if (response.statusCode() == 404) {
            log.info("Url {} has been deleted", url.url());
            urlAnalyzedSet.add(new UrlAnalyzed(url.id(), url.url(), true, ""));
            return;
        }
        if (response.statusCode() == 500) {
            // Telegraph иногда не держит нагрузку и ломается, поэтому пока откидываем этот url в карантин за счет лока
            return;
        }

        String body = response.body();
        labels.addAll(labelService.parseLabels(url.id(), body));
        telegraphUrls.addAll(telegraphUrlsService.parseTelegraphUrls(url, body));

        Set<ExternalUrl> unclearExternalUrls = externalUrlsService.parseExternalUrls(url.id(), body);
        // Вытаскиваем telegraph ссылки из external
        Set<TelegraphUrl> uncheckedTelegraphUrls = unclearExternalUrls.stream()
                        .map(ExternalUrl::url)
                        .filter(external -> external.startsWith(TELEGRAPH_PREFIX))
                        .map(external -> new TelegraphUrl(url.id(), external))
                .collect(Collectors.toSet());
        // Сохраняем рабочие
        telegraphUrls.addAll(
                checkTelegraphUrls(uncheckedTelegraphUrls)
        );

        externalUrls.addAll(
                externalUrls.stream()
                        .filter(externalUrl -> !externalUrl.url().startsWith(TELEGRAPH_PREFIX))
                        .collect(Collectors.toSet())
        );

        urlAnalyzedSet.add(new UrlAnalyzed(url.id(), url.url(), false, Analyzer.parseSavedAt(body)));
    }

    /**
     * Вытащить все рабочие ссылки telegra.ph/... и проверить их
     */
    private Set<TelegraphUrl> checkTelegraphUrls(Set<TelegraphUrl> urls) throws InterruptedException {
        Set<TelegraphUrl> checkedUrls = new HashSet<>();
        for (TelegraphUrl url : urls) {
            while (true) {
                int statusCode = httpClient.headUrl(url.url());
                if (statusCode == 200) {
                    checkedUrls.add(url);
                    break;
                } else if (statusCode != 500) {
                    break;
                }
            }
        }

        return checkedUrls;
    }

    @Override
    protected String getThreadNamePrefix() {
        return THREAD_NAME_PREFIX;
    }

    @Override
    protected int getThreadCount() {
        return indexerProperties.getThreadCount();
    }
}
