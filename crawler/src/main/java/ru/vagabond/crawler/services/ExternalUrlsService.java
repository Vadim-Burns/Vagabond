package ru.vagabond.crawler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vagabond.crawler.daos.ExternalUrlDao;
import ru.vagabond.crawler.enums.ExternalUrlType;
import ru.vagabond.crawler.models.ExternalUrl;
import ru.vagabond.crawler.utils.Analyzer;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalUrlsService {

    private final ExternalUrlDao externalUrlDao;

    public void saveExternalUrls(Set<ExternalUrl> externalUrls) {
        log.info("Saving {} external urls", externalUrls.size());
        externalUrlDao.saveExternalUrls(externalUrls);
    }

    public Set<ExternalUrl> parseExternalUrls(Long sourceUrlId, String body) {
        Set<ExternalUrl> externalUrls = new HashSet<>();
        externalUrls.addAll(
                Analyzer.findExternalDisks(body)
                        .stream()
                        .map(url -> new ExternalUrl(url, ExternalUrlType.EXTERNAL_DISK, sourceUrlId))
                        .toList()
        );
        externalUrls.addAll(
                Analyzer.findSocialNetworks(body)
                        .stream()
                        .map(url -> new ExternalUrl(url, ExternalUrlType.SOCIAL_NETWORK, sourceUrlId))
                        .toList()
        );
        externalUrls.addAll(
                Analyzer.findTelegramLinks(body)
                        .stream()
                        .map(url -> new ExternalUrl(url, ExternalUrlType.TELEGRAM_LINK, sourceUrlId))
                        .toList()
        );
        externalUrls.addAll(
                Analyzer.findIps(body)
                        .stream()
                        .map(url -> new ExternalUrl(url, ExternalUrlType.IP, sourceUrlId))
                        .toList()
        );
        externalUrls.addAll(
                Analyzer.findUrls(body)
                        .stream()
                        .map(url -> new ExternalUrl(url, ExternalUrlType.OTHER, sourceUrlId))
                        .toList()
        );

        return externalUrls;
    }
}
