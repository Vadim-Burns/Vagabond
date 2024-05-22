package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.models.TelegraphUrl;

import java.util.Collection;

@Dao
@RequiredArgsConstructor
public class FileDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void saveFoundFiles(Collection<TelegraphUrl> urls) {
        if (urls.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        with creating_files as (
                            insert into vagabond.files
                                (url)
                            values
                                (:url)
                            on conflict do nothing
                            returning id
                        ), created_files as (
                            select
                                id
                            from vagabond.files
                            where url in (:url)
                        )
                        insert into vagabond.url_file
                            (url_id, file_id)
                        select
                            :source_url_id as url_id,
                            id as file_id
                        from created_files
                        union all
                        select
                            :source_url_id as url_id,
                            id as file_id
                        from creating_files
                        on conflict do nothing
                        """,
                urls.stream()
                        .map(url -> new MapSqlParameterSource()
                                .addValue("url", url.url())
                                .addValue("source_url_id", url.sourceUrlId())
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }
}
