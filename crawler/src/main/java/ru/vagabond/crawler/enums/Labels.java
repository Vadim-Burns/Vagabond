package ru.vagabond.crawler.enums;

import java.util.Set;

public enum Labels implements HasId<String> {

    CREDENTIALS("credentials", Set.of(
            "admin", "root", "password", "ssh://", "OPENSSH PRIVATE KEY", "port"
    )),
    MAILS("mail", Set.of(
            "@gmail", "@mail", "@yahoo", "@yandex"
    )),
    RUSSIAN_NUMBERS("russain_numbers", Set.of(
            "+79", "+7 (9"
    )),
    HACK("hack", Set.of(
            "hack", "vzlom", "взлом", "кража"
    )),
    NSFW("nsfw", Set.of(
            "r34", "nsfw", "teen", "underage", "rape", "изнасилование", "похищение"
    ));

    private final String id;
    private final Set<String> keyWords;

    Labels(String id, Set<String> keyWords) {
        this.id = id;
        this.keyWords = keyWords;
    }

    @Override
    public String getId() {
        return id;
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }
}
