package ru.vagabond.crawler.infra.environment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentDao environmentDao;

    @Override
    public Optional<String> getValue(String name) {
        log.info("Getting '{}' name", name);
        return environmentDao.getValue(name);
    }

    @Override
    public Optional<Integer> getValueInt(String name) {
        return getValue(name).map(Integer::parseInt);
    }

    @Override
    public Optional<Boolean> getValueBool(String name) {
        return getValue(name).map(Boolean::valueOf);
    }
}
