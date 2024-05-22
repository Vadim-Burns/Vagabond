package ru.vagabond.crawler.core.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.vagabond.crawler.infra.environment.EnvironmentService;

public abstract class AbstractExecutor {

    private static final String JOB_DISABLE_SUFFIX_ENV = ".disabled";

    @Autowired
    private EnvironmentService environmentService;

    @Value("${hostname}")
    private String hostname;


    public abstract void execute();

    public final void start() {
        new Thread(this::executeInternal).start();
    }

    private void executeInternal() {
        final String name = getExecutorName();
        Logger logger = LoggerFactory.getLogger(getClass());

        if (isExecutorDisabled(name)) {
            logger.info("[{} executor disabled]", name);
            return;
        }

        final String originalName = Thread.currentThread().getName();
        Thread.currentThread().setName(name);

        try {
            beforeLaunch();
            logger.info("[{} executor started]", name);
            execute();
            onSuccess();
            logger.info("[{} executor successed]", name);
        } catch (RuntimeException ex) {
            logger.error("[{} executor crushed]", name);
            logger.error("", ex);
            onError(ex);
            throw ex;
        } finally {
            logger.info("[{} executor finished]", name);
            afterLaunch();
            Thread.currentThread().setName(originalName);
        }
    }

    public String getExecutorName() {
        return getClass().getSimpleName();
    }

    protected void beforeLaunch() {
    }

    protected void afterLaunch() {
    }

    protected void onError(Exception ex) {
    }

    protected void onSuccess() {
    }

    private boolean isExecutorDisabled(String executorName) {
        return environmentService.getValueBool(
                executorName + "." + hostname + JOB_DISABLE_SUFFIX_ENV
        ).orElse(false);
    }
}
