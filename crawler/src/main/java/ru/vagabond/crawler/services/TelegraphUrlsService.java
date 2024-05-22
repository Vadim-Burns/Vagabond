package ru.vagabond.crawler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vagabond.crawler.daos.FileDao;
import ru.vagabond.crawler.daos.UrlDao;
import ru.vagabond.crawler.models.TelegraphUrl;
import ru.vagabond.crawler.models.UrlAnalyzed;
import ru.vagabond.crawler.models.UrlToAnalyze;
import ru.vagabond.crawler.utils.Analyzer;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class TelegraphUrlsService {

    // сколько url-ов блочим в базе за раз
    private static final int SELECT_URLS_AT_ONCE = 20000;
    private static final String TELEGRAPH_FILE_PREFIX = "https://telegra.ph/file/";
    private static final String TELEGRAPH_PREFIX = "https://telegra.ph/";

    private final UriService uriService;
    private final UrlDao urlDao;
    private final FileDao fileDao;

    private final Set<UrlToAnalyze> lockedUrls = new HashSet<>();

    public synchronized List<UrlToAnalyze> selectUrlsToAnalyze(int count) {
        if (lockedUrls.isEmpty()) {
            lockedUrls.addAll(urlDao.foundUrlForAnalyze(SELECT_URLS_AT_ONCE));
            log.info("Got {} urls to analyze", lockedUrls.size());
        }

        List<UrlToAnalyze> urlToAnalyzeList = new ArrayList<>();
        while (count > 0) {
            for (UrlToAnalyze url : lockedUrls) {
                urlToAnalyzeList.add(url);
                lockedUrls.remove(url);
                break;
            }
            count--;
        }

        return urlToAnalyzeList;
    }

    /**
     * 1. Вытаскиваем и отдельно сохраняем ссылки на файлы telegraph
     * <p>
     * 2. Разбираем найденные telegraph статьи на uri-и по символам и по отдельным словам(те что через дефис)
     * и сохраняем
     * <p>
     * 3. Сохраняем telegraph ссылки
     * <p>
     * 4. Обновляем статус url-а
     *
     * @param urls         Точно существующие ссылки telegraph
     */
    public void saveAnalyzedUrls(Set<UrlAnalyzed> urlAnalyzedSet, Set<TelegraphUrl> urls) {
        log.info("Saving {} telegraph urls", urls.size());

        if (!urls.isEmpty()) {
            saveFoundUris(urls.stream().map(TelegraphUrl::url).filter(url -> !url.startsWith(TELEGRAPH_FILE_PREFIX)).toList());
            saveFoundFiles(urls.stream().filter(url -> url.url().startsWith(TELEGRAPH_FILE_PREFIX)).toList());
            saveFoundUrls(urls.stream().filter(url -> !url.url().startsWith(TELEGRAPH_FILE_PREFIX)).toList());
        }

        urlDao.updateProcessedUrls(urlAnalyzedSet);
    }

    /**
     * Парсим найденные url-ы и разбираем их на uri по символам и словам
     *
     * @param urls
     */
    private void saveFoundUris(List<String> urls) {
        Set<String> newUris = new HashSet<>();
        for (String url : urls) {
            url = url.replaceAll(TELEGRAPH_PREFIX, "");

            String[] words = url.split("-");
            StringBuilder builder = new StringBuilder();
            for (String word : words) {
                // Слова по отдельности
                newUris.add(word);

                // Лесенкой добавляем url по словам
                if (!builder.toString().isEmpty()) {
                    builder.append('-');
                }
                builder.append(word);
                newUris.add(builder.toString());
            }

            newUris.addAll(List.of(url.split("")));
        }
        newUris.remove("-");

        log.info("Generated {} uris", newUris.size());
        uriService.saveNewUris(newUris);
    }

    public Set<TelegraphUrl> parseTelegraphUrls(UrlToAnalyze url, String body) {
        Set<String> telegraphUrls = Analyzer.findTelegraphUrls(body);
        telegraphUrls.addAll(Analyzer.findHtmlUris(body));
        telegraphUrls.remove(url.url());

        return telegraphUrls.stream()
                .map(telegraphUrl -> new TelegraphUrl(url.id(), telegraphUrl))
                .collect(Collectors.toSet());
    }

    /**
     * Save urls found from crawler
     */
    public void saveFoundUrls(Set<String> urls) {
        urlDao.saveFoundUrls(urls);
    }

    private void saveFoundFiles(Collection<TelegraphUrl> urls) {
        log.info("Saving {} telegraph files", urls.size());
        fileDao.saveFoundFiles(urls);
    }

    private void saveFoundUrls(Collection<TelegraphUrl> urls) {
        log.info("Saving {} found telegraph urls", urls.size());
        urlDao.saveIndexerFoundUrls(urls);
    }
}
