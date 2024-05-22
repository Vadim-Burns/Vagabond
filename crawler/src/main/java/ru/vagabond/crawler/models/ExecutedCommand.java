package ru.vagabond.crawler.models;

import java.util.Date;

public record ExecutedCommand(Long id, String result, Date startedAt) {
}
