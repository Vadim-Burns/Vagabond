package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.models.Label;

import java.util.Collection;

@Dao
@RequiredArgsConstructor
public class LabelDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void saveLabels(Collection<Label> labels) {
        if (labels.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        insert into vagabond.label
                            (source_url_id, reason, cnt)
                        values
                            (:source_url_id, :reason, :cnt)
                        on conflict do nothing
                        """,
                labels.stream()
                        .map(label -> new MapSqlParameterSource()
                                .addValue("source_url_id", label.sourceUrlId())
                                .addValue("reason", label.reason())
                                .addValue("cnt", label.count())
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }

}
