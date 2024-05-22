package ru.vagabond.crawler.core.extension;

import java.util.List;
import java.util.Optional;

public class ListExtension {

    public static <T> Optional<T> getFirstOptional(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.getFirst());
    }
}
