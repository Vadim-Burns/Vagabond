package ru.vagabond.crawler.infra.environment;

import java.util.Optional;

public interface EnvironmentService {

    Optional<String> getValue(String name);

    Optional<Integer> getValueInt(String name);

    Optional<Boolean> getValueBool(String name);
}
