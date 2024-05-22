package ru.vagabond.crawler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.vagabond.crawler.daos.LabelDao;
import ru.vagabond.crawler.enums.Labels;
import ru.vagabond.crawler.models.Label;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelDao labelDao;

    public void saveLabels(Set<Label> labels) {
        log.info("Saving {} labels", labels.size());
        labelDao.saveLabels(labels);
    }

    public Set<Label> parseLabels(Long sourceUrlId, String body) {
        Set<Label> labels = new HashSet<>();
        for (var label : Labels.values()) {
            Long count = 0L;
            for (String keyWord : label.getKeyWords()) {
                count += StringUtils.countMatches(body, keyWord);
            }

            if (count > 0) {
                labels.add(new Label(sourceUrlId, label.getId(), count));
            }
        }

        return labels;
    }
}
