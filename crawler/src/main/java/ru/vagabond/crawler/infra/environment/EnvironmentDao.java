package ru.vagabond.crawler.infra.environment;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.core.extension.ListExtension;

import java.util.Optional;

@Dao
@ExtensionMethod(ListExtension.class)
@RequiredArgsConstructor
public class EnvironmentDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<String> getValue(String name) {
        return jdbcTemplate.query(
                """
                        select 
                            *
                        from infra.environment
                        where name = :name
                        """,
                new MapSqlParameterSource()
                        .addValue("name", name),
                (rs, rn) -> rs.getString("value")
        ).getFirstOptional();
    }
}
