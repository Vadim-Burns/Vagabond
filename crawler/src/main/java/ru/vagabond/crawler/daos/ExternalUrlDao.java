package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.models.ExternalUrl;

import java.util.Collection;

@Dao
@RequiredArgsConstructor
public class ExternalUrlDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void saveExternalUrls(Collection<ExternalUrl> urls) {
        if (urls.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        with creating_externals as (
                            insert into vagabond.external_url
                                (url, type)
                            values
                                (:url, :type::vagabond.external_url_type)
                            on conflict do nothing
                            returning id
                        ), created_externals as (
                            select
                                id
                            from vagabond.external_url
                            where url in (:url)
                        )
                        insert into vagabond.url_external_url
                            (source_url_id, external_url_id)
                        select
                            :source_url_id as source_url_id,
                            id as external_url_id
                        from created_externals
                        union all
                        select
                            :source_url_id as source_url_id,
                            id as external_url_id
                        from creating_externals
                        on conflict do nothing
                        """,
                urls.stream()
                        .map(url -> new MapSqlParameterSource()
                                .addValue("url", url.url())
                                .addValue("type", url.type().getId())
                                .addValue("source_url_id", url.sourceUrlId())
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }

}
