package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Dao
@RequiredArgsConstructor
public class UriDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<String> selectNewUriForUpdate(int count) {
        return jdbcTemplate.query(
                """
                        update vagabond.uri
                        set is_locked = true, locked_at = current_timestamp
                        where uri in (
                            select
                                uri
                            from vagabond.uri
                            where not is_locked
                                and status = 'new'
                            limit :cnt
                            for update
                            skip locked
                        )
                        returning uri
                        """,
                new MapSqlParameterSource()
                        .addValue("cnt", count),
                (rs, rn) -> rs.getString("uri")
        );
    }

    public void updateProcessedUri(String uri) {
        jdbcTemplate.update(
                """
                        update vagabond.uri
                        set status = 'checked', is_locked = false, locked_at = null
                        where uri = :uri
                        """,
                new MapSqlParameterSource()
                        .addValue("uri", uri)
        );
    }

    public int unlockUris(OffsetDateTime unlockDatetime) {
        return jdbcTemplate.update(
                """
                        update vagabond.uri
                        set is_locked = false, locked_at = null
                        where locked_at < :unlockDatetime
                        """,
                new MapSqlParameterSource()
                        .addValue("unlockDatetime", unlockDatetime)
        );
    }

    public void saveNewUris(Collection<String> uris) {
        if (uris.isEmpty()) {
            return;
        }

        jdbcTemplate.batchUpdate(
                """
                        insert into vagabond.uri
                            (uri, source)
                        values
                            (:uri, :source::vagabond.uri_source_type)
                        on conflict do nothing
                        """,
                uris.stream()
                        .map(uri -> new MapSqlParameterSource()
                                .addValue("uri", uri)
                                .addValue("source", "indexer")
                        ).toArray(MapSqlParameterSource[]::new)
        );
    }
}
