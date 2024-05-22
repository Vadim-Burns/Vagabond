package ru.vagabond.crawler.executors;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionTemplate;
import ru.vagabond.crawler.core.executor.AbstractMultiThreadExecutor;
import ru.vagabond.crawler.core.executor.Executor;
import ru.vagabond.crawler.infra.environment.EnvironmentService;
import ru.vagabond.crawler.properties.TransactionalProperties;
import ru.vagabond.crawler.services.TransactionalExecutionService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Сервис выполнения больших транзакций в бд.
 */
@Slf4j
@Executor
@RequiredArgsConstructor
public class TransactionalExecutor extends AbstractMultiThreadExecutor {

    // Сколько спим, если нечего выполнять
    private final static int SLEEP_TIME_DEFAULT = 1000 * 60;
    private final static String SLEEP_TIME_ENV = "transactional.sleep-time";
    private final static String THREAD_NAME_PREFIX = "TransTP-";

    private final TransactionalExecutionService transactionalExecutionService;
    private final TransactionTemplate transactionTemplate;
    private final TransactionalProperties transactionalProperties;
    private final EnvironmentService environmentService;

    private Supplier<Integer> sleepTimeSupplier;

    @PostConstruct
    private void postConstruct() {
        log.info("Init suppliers");
        sleepTimeSupplier = Suppliers.memoizeWithExpiration(
                () -> this.environmentService.getValueInt(SLEEP_TIME_ENV).orElse(SLEEP_TIME_DEFAULT),
                5,
                TimeUnit.MINUTES
        );
    }

    @Override
    protected void executeSingleThread() {
        while (true) {
            try {
                Callable<Void> toExecute = transactionalExecutionService.pollCallableToExecute();
                if (toExecute == null) {
                    log.info("Nothing to execute. Sleeping");
                    Thread.sleep(sleepTimeSupplier.get());
                    continue;
                }

                transactionTemplate.execute(status -> {
                    try {
                        log.info("ExecuteTransactional");
                        toExecute.call();
                    } catch (InterruptedException ex) {
                        log.info("Interrupting");
                    } catch (Exception ex) {
                        log.error("Error during execution", ex);
                    }

                    return null;
                });
            } catch (InterruptedException ex) {
                log.info("Interrupting");
                break;
            } catch (Exception ex) {
                log.error("Error during executing", ex);
            }
        }
    }

    @Override
    protected String getThreadNamePrefix() {
        return THREAD_NAME_PREFIX;
    }

    @Override
    protected int getThreadCount() {
        return transactionalProperties.getThreadCount();
    }
}
