package ru.vagabond.crawler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vagabond.crawler.daos.UriDao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UriService {

    // Сколько uri-ев мы забираем и блочим в базе за раз
    private static final int SELECT_URI_AT_ONCE = 3000;

    private final UriDao uriDao;

    private final Set<String> lockedUris = new HashSet<>();

    /**
     * Get uri for check
     */
    public synchronized Optional<String> selectUriForCheck() {
        if (lockedUris.isEmpty()) {
            retrieveUris();
            log.info("Got {} uris for check", lockedUris.size());
        }

        for (String uri : lockedUris) {
            lockedUris.remove(uri);
            return Optional.of(uri);
        }

        return Optional.empty();
    }

    private void retrieveUris() {
        lockedUris.addAll(
                uriDao.selectNewUriForUpdate(SELECT_URI_AT_ONCE)
        );
    }

    public void updateProcessedUri(String uri) {
        uriDao.updateProcessedUri(uri);
    }

    public void saveNewUris(Set<String> newUris) {
        uriDao.saveNewUris(newUris);
    }
}
