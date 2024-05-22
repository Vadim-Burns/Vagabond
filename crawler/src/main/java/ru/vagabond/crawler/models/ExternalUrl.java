package ru.vagabond.crawler.models;

import ru.vagabond.crawler.enums.ExternalUrlType;

public record ExternalUrl(String url, ExternalUrlType type, Long sourceUrlId) {
}
