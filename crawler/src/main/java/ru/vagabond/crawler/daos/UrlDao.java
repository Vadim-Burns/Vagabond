package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.models.TelegraphUrl;
import ru.vagabond.crawler.models.UrlAnalyzed;
import ru.vagabond.crawler.models.UrlToAnalyze;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Dao
@RequiredArgsConstructor
public class UrlDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void saveFoundUrls(Collection<String> foundUrls) {
        if (foundUrls.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        insert into vagabond.url
                            (url)
                        values
                            (:url)
                        on conflict do nothing
                        """,
                foundUrls.stream()
                        .map(url -> new MapSqlParameterSource()
                                .addValue("url", url)
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }

    public List<UrlToAnalyze> foundUrlForAnalyze(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count can't be less than one!");
        }

        return jdbcTemplate.query(
                """
                        update vagabond.url
                        set is_locked = true, locked_at = current_timestamp
                        where id in (
                            select
                                id
                            from vagabond.url
                            where status = 'new'
                                and not is_locked
                            limit :limit
                            for update
                            skip locked
                        )
                        returning id, url
                        """,
                new MapSqlParameterSource()
                        .addValue("limit", count),
                (rs, rn) -> new UrlToAnalyze(
                        rs.getLong("id"),
                        rs.getString("url")
                )
        );
    }

    public int unlockUrls(OffsetDateTime unlockDatetime) {
        return jdbcTemplate.update(
                """
                        update vagabond.url
                        set is_locked = false, locked_at = null
                        where locked_at < :unlockDatetime
                        """,
                new MapSqlParameterSource()
                        .addValue("unlockDatetime", unlockDatetime)
        );
    }

    public void updateProcessedUrl(Long id, boolean isDeleted, String savedAt) {
        jdbcTemplate.update(
                """
                        update vagabond.url
                        set is_locked = false, locked_at = null, saved_at = :savedAt, is_deleted = :isDeleted, status = 'analyzed'
                        where id = :id
                        """,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("savedAt", savedAt)
                        .addValue("isDeleted", isDeleted)
        );
    }

    public void updateProcessedUrls(Collection<UrlAnalyzed> urls) {
        if (urls.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        insert into vagabond.url
                            (id, url, is_deleted, saved_at, is_locked, locked_at, status)
                        values
                            (:id, :url, :isDeleted, :savedAt, false, null, 'analyzed')
                        on conflict (id) do update
                        set is_deleted = excluded.is_deleted, saved_at = excluded.saved_at,
                            is_locked = excluded.is_locked, locked_at = excluded.locked_at,
                            status = excluded.status
                        """,
                urls.stream()
                        .map(url -> new MapSqlParameterSource()
                                .addValue("id", url.id())
                                .addValue("url", url.url())
                                .addValue("isDeleted", url.isDeleted())
                                .addValue("savedAt", url.savedAt())
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }

    public void saveIndexerFoundUrls(Collection<TelegraphUrl> urls) {
        if (urls.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        with creating_urls as (
                            insert into vagabond.url
                                (url)
                            values
                                (:url)
                            on conflict do nothing
                            returning id
                        ), created_urls as (
                            select
                                id
                            from vagabond.url
                            where url in (:url)
                        )
                        insert into vagabond.url_url
                            (source_url_id, target_url_id)
                        select
                            :source_url_id as source_url_id,
                            id
                        from created_urls
                        union all
                        select
                            :source_url_id as source_url_id,
                            id
                        from creating_urls
                        on conflict do nothing
                        """,
                urls.stream()
                        .map(url -> new MapSqlParameterSource()
                                .addValue("url", url.url())
                                .addValue("source_url_id", url.sourceUrlId())
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }

    public int resetUrls() {
        return jdbcTemplate.update(
                """
                        update vagabond.url
                        set status = 'new'
                        where status = 'analyzed'
                        """,
                new MapSqlParameterSource()
        );
    }

    public String getUrlStats() {
        List<String> stats = jdbcTemplate.query(
                """
                        select
                            count(*),
                            status
                        from vagabond.url
                        group by status
                        """,
                (rs, rn) -> "count = %s status = %s".formatted(rs.getString("count"), rs.getString("status"))
        );

        return String.join("\n", stats);
    }
}
