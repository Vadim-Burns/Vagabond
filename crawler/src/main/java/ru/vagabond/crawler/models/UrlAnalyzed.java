package ru.vagabond.crawler.models;

public record UrlAnalyzed(Long id, String url, boolean isDeleted, String savedAt) {
}
