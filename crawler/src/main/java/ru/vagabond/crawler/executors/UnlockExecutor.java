package ru.vagabond.crawler.executors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.vagabond.crawler.core.executor.AbstractExecutor;
import ru.vagabond.crawler.core.executor.Executor;
import ru.vagabond.crawler.daos.UriDao;
import ru.vagabond.crawler.daos.UrlDao;
import ru.vagabond.crawler.infra.environment.EnvironmentService;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Executor для снятия локов на uri(например в случае перезагрузки)
 */
@Slf4j
@Executor
@RequiredArgsConstructor
public class UnlockExecutor extends AbstractExecutor {

    // Минуты
    private static final String LOCK_TIMEOUT_ENV = "lock.timeout.minutes";
    private static final int LOCK_TIMEOUT_DEFAULT = 10;
    private static final String SLEEP_TIME_ENV = "lock.sleep.minutes";
    private static final int SLEEP_TIME_DEFAULT = 1;

    private final UriDao uriDao;
    private final UrlDao urlDao;
    private final EnvironmentService environmentService;

    @Override
    public void execute() {
        while (true) {
            try {
                int lockTimeout = getLockTimeout();
                log.info("Got lock timeout is {} minutes", lockTimeout);
                OffsetDateTime unlockDatetime = OffsetDateTime.now().minusMinutes(lockTimeout);
                log.info("Unlock datetime is {}", unlockDatetime);

                int unlockedUris = uriDao.unlockUris(unlockDatetime);
                log.info("Unlocked {} uris", unlockedUris);

                int unlockedUrls = urlDao.unlockUrls(unlockDatetime);
                log.info("Unlocked {} urls", unlockedUrls);

                int sleepTime = getSleepTime();
                log.info("Sleep for {} minutes", sleepTime);
                TimeUnit.MINUTES.sleep(sleepTime);
            } catch (InterruptedException ex) {
                log.error("UnlockExecutor interrupted, quiting...", ex);
                break;
            } catch (Exception ex) {
                log.error("Unlock exception", ex);
            }
        }
    }

    private int getLockTimeout() {
        return environmentService.getValueInt(LOCK_TIMEOUT_ENV).orElse(LOCK_TIMEOUT_DEFAULT);
    }

    private int getSleepTime() {
        return environmentService.getValueInt(SLEEP_TIME_ENV).orElse(SLEEP_TIME_DEFAULT);
    }
}
