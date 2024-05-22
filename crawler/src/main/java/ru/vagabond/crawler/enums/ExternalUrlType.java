package ru.vagabond.crawler.enums;

public enum ExternalUrlType implements HasId<String> {
    EXTERNAL_DISK("external_disk"),
    SOCIAL_NETWORK("social_network"),
    TELEGRAM_LINK("telegram_link"),
    IP("ip"),
    OTHER("other");

    private final String id;

    ExternalUrlType(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
