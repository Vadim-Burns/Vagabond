package ru.vagabond.crawler.core.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class AbstractMultiThreadExecutor extends AbstractExecutor {

    @Override
    public final void execute() {
        int threadCount = getThreadCount();
        log.info("Thread count is {}", threadCount);

        List<Callable<Void>> threadTasks = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            int threadNumber = i;
            threadTasks.add(() -> executeTask(threadNumber));
        }
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            log.info("Start threads");
            executorService.invokeAll(threadTasks);
        } catch (InterruptedException ex) {
            log.info("Stopping threads");
        }
    }

    /**
     * Preconfigure thread
     *
     * @param threadNumber номер потока
     */
    private Void executeTask(Integer threadNumber) {
        String threadName = getThreadNamePrefix() + threadNumber.toString();
        Thread.currentThread().setName(threadName);
        log.info("Starting thread {}", threadName);
        executeSingleThread();
        return null;
    }

    /**
     * Get thread name prefix
     */
    protected abstract String getThreadNamePrefix();

    /**
     * Executes in every thread of executor pool
     */
    protected abstract void executeSingleThread();

    /**
     * Get thread count
     */
    protected abstract int getThreadCount();
}
