package ru.vagabond.crawler.configs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import ru.vagabond.crawler.core.executor.AbstractExecutor;
import ru.vagabond.crawler.core.executor.Executor;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExecutorsConfiguration {

    private final ApplicationContext applicationContext;

    @PostConstruct
    public void startExecutors() {
        Map<String, Object> executors = applicationContext.getBeansWithAnnotation(Executor.class);
        log.info("Got {} executors", executors.keySet().size());

        executors.forEach((key, value) -> {
            log.info("Starting {} executor", key);
            ((AbstractExecutor) value).start();
        });
    }
}
