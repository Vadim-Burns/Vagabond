package ru.vagabond.crawler.core.extension;

import java.util.Optional;

public class AnyExtension {

    public static <T> Optional<T> optional(T obj) {
        return Optional.ofNullable(obj);
    }
}
