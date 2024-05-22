package ru.vagabond.crawler.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class Analyzer {

    private final Set<Character> urlEndCharacters = Set.of(
            ' ', '\n', '\t', '"'
    );
    private final Set<Character> telegraphPermittedCharacters = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '/', '-', '?', '=', '_', '#', '!', '%', '.'
    );
    private final Set<Character> externalDiskPermittedCharacters = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '/', '-', '?', '=', '_', '#', '!', '%'
    );
    private final Set<Character> socialNetworksPermittedCharacters = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '/', '-', '?', '=', '_', '#', '!', '%'
    );
    private final Set<Character> telegramPermittedCharacters = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            '/', '-', '?', '=', '_', '#', '!', '%'
    );
    private final Set<String> htmlPrefixes = Set.of(
            "src=\"", "href=\""
    );
    // Файлы, которые нас не интересуют
    private final Set<String> htmlNotRelevantPrefixes = Set.of(
            "https://telegra.ph/css/",
            "https://telegra.ph/favicon.ico",
            "https://telegra.ph/images/",
            "https://telegra.ph/js/",
            "https://telegra.ph/mailto:dmca@",
            "https://telegra.ph/https://t.me/_websync_?",
            "https://t.me/_websync_"
    );

    private final String httpsPrefix = "https://";
    private final String telegraphPrefix = "telegra.ph";
    private final String telegraphFilePrefix = "https://telegra.ph/file/";
    private final Set<String> telegraphFileEnds = Set.of(
            ".png", ".jpeg", ".jpg", ".pdf", ".fb2", ".mp3", ".mp4", ".mpa",
            ".wav", ".mov", ".avi", ".wmv", ".gif", ".jfif", ".svg", ".avif", ".apng", ".pjpeg", ".pjp"
    );
    private final Set<String> telegraphPossibleLastChars = Set.of(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    );
    private final Set<String> externalDisks = Set.of(
            "cloud.mail.ru/",
            "disk.yandex.ru/",
            "yadi.sk/",
            "drive.google.com/",
            "mega.nz/"
    );
    private final Set<String> socialNetworks = Set.of(
            "vk.com/",
            "ok.ru/",
            "fb.com/",
            "facebook.com/",
            "instagram.com/"
    );
    private final String telegramPrefix = "t.me/";
    private final Set<Pattern> ipRegexes = Set.of(
            // IPv4
            Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})"),
            // IPv6
            Pattern.compile("((([0-9a-fA-F]){1,4})\\:){7}([0-9a-fA-F]){1,4}")
    );
    private final Set<Pattern> urlRegexes = Set.of(
            Pattern.compile("(http|ftp|https|psql|ssh):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])\n")
    );
    private final String savedAtPrefix = "<time datetime=\"";

    /**
     * Поиск всех ссылок формата telegra.ph/....
     *
     * @param body html source code
     * @return set возможных ссылок(нужно проверить их существование)
     */
    public Set<String> findTelegraphUrls(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        int lastIndex = 0;
        while (true) {
            lastIndex = body.indexOf(telegraphPrefix, lastIndex);
            if (lastIndex == -1) {
                break;
            }
            lastIndex += 10;

            StringBuilder builder = new StringBuilder();
            builder.append(httpsPrefix);
            builder.append(telegraphPrefix);
            builder.append('/');
            for (int i = lastIndex; i < body.length(); i++) {
                if (!telegraphPermittedCharacters.contains(body.charAt(i))) {
                    break;
                }

                builder.append(body.charAt(i));
            }
            urls.add(builder.toString());
            lastIndex++;
        }

        return normalizeForTelegraph(filterUrls(urls));
    }

    /**
     * Поиск telegraph uri-ев в html объектах
     *
     * @param body html source code
     * @return set возможных ссылок
     */
    public Set<String> findHtmlUris(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        for (String htmlPrefix : htmlPrefixes) {
            int lastIndex = 0;
            while (true) {
                lastIndex = body.indexOf(htmlPrefix, lastIndex);
                if (lastIndex == -1) {
                    break;
                }
                lastIndex += htmlPrefix.length();

                StringBuilder builder = new StringBuilder();
                builder.append(httpsPrefix);
                builder.append(telegraphPrefix);

                if (body.charAt(lastIndex) != '/') {
                    builder.append('/');
                }

                for (int i = lastIndex; i < body.length(); i++) {
                    if (!telegraphPermittedCharacters.contains(body.charAt(i))) {
                        break;
                    }

                    builder.append(body.charAt(i));
                }
                urls.add(builder.toString());
                lastIndex++;
            }
        }

        return normalizeForTelegraph(filterUrls(urls));
    }

    public Set<String> findExternalDisks(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        for (String diskPrefix : externalDisks) {
            int lastIndex = 0;
            while (true) {
                lastIndex = body.indexOf(diskPrefix, lastIndex);
                if (lastIndex == -1) {
                    break;
                }
                lastIndex += diskPrefix.length();

                StringBuilder builder = new StringBuilder();
                builder.append(httpsPrefix);
                builder.append(diskPrefix);

                for (int i = lastIndex; i < body.length(); i++) {
                    if (!externalDiskPermittedCharacters.contains(body.charAt(i))) {
                        break;
                    }

                    builder.append(body.charAt(i));
                }

                urls.add(builder.toString());
                lastIndex++;
            }
        }

        return urls;
    }

    public Set<String> findSocialNetworks(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        for (String socialNetwork : socialNetworks) {
            int lastIndex = 0;
            while (true) {
                lastIndex = body.indexOf(socialNetwork, lastIndex);
                if (lastIndex == -1) {
                    break;
                }
                lastIndex += socialNetwork.length();

                StringBuilder builder = new StringBuilder();
                builder.append(httpsPrefix);
                builder.append(socialNetwork);

                for (int i = lastIndex; i < body.length(); i++) {
                    if (!socialNetworksPermittedCharacters.contains(body.charAt(i))) {
                        break;
                    }

                    builder.append(body.charAt(i));
                }

                urls.add(builder.toString());
                lastIndex++;
            }
        }

        return urls;
    }

    public Set<String> findTelegramLinks(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        int lastIndex = 0;
        while (true) {
            lastIndex = body.indexOf(telegramPrefix, lastIndex);
            if (lastIndex == -1) {
                break;
            }
            lastIndex += telegramPrefix.length();

            StringBuilder builder = new StringBuilder();
            builder.append(httpsPrefix);
            builder.append(telegramPrefix);

            for (int i = lastIndex; i < body.length(); i++) {
                if (!telegramPermittedCharacters.contains(body.charAt(i))) {
                    break;
                }

                builder.append(body.charAt(i));
            }

            urls.add(builder.toString());
            lastIndex++;
        }

        return filterUrls(urls);
    }

    public Set<String> findIps(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> ips = new HashSet<>();
        for (Pattern regex : ipRegexes) {
            Matcher matcher = regex.matcher(body);
            while (matcher.find()) {
                ips.add(matcher.group().strip());
            }
        }

        return ips;
    }

    public Set<String> findUrls(String body) {
        if (body.isBlank()) {
            return Set.of();
        }

        Set<String> urls = new HashSet<>();
        for (Pattern regex : urlRegexes) {
            Matcher matcher = regex.matcher(body);
            while (matcher.find()) {
                urls.add(matcher.group().strip());
            }
        }

        return urls;
    }

    public String parseSavedAt(String body) {
        if (body.isBlank()) {
            return "";
        }

        int index = 0;
        index = body.indexOf(savedAtPrefix, index);
        if (index == -1) {
            return "";
        }
        index += savedAtPrefix.length();

        StringBuilder builder = new StringBuilder();
        for (int i = index; i < body.length(); i++) {
            if (body.charAt(i) == '"') {
                break;
            }

            builder.append(body.charAt(i));
        }

        return builder.toString();
    }

    private Set<String> filterUrls(Set<String> urls) {
        Set<String> newUrls = new HashSet<>();
        for (var url : urls) {
            boolean failed = false;
            for (var prefix : htmlNotRelevantPrefixes) {
                if (url.startsWith(prefix)) {
                    failed = true;
                    break;
                }
            }

            if (failed) continue;
            newUrls.add(url);
        }

        return newUrls;
    }

    private Set<String> normalizeForTelegraph(Set<String> urls) {
        Set<String> newUrls = new HashSet<>();
        for (String url : urls) {
            try {
                newUrls.add(
                        URLDecoder.decode(
                                url
                        ).replaceAll("telegra.ph//", "telegra.ph/").toLowerCase()
                );
            } catch (IllegalArgumentException ignored) {
            }
        }

        return newUrls.stream()
                .filter(url -> {
                    if (url.startsWith(telegraphFilePrefix)) {
                        return telegraphFileEnds.stream().anyMatch(url::endsWith);
                    } else {
                        return telegraphPossibleLastChars.stream().anyMatch(url::endsWith)
                                && StringUtils.countMatches(url, '-') >= 2;
                    }
                })
                .collect(Collectors.toSet());
    }
}
