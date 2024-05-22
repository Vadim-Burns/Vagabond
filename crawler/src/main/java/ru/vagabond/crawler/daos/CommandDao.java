package ru.vagabond.crawler.daos;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.vagabond.crawler.core.dao.Dao;
import ru.vagabond.crawler.models.CommandToExecute;
import ru.vagabond.crawler.models.ExecutedCommand;

import java.util.List;

@Dao
@RequiredArgsConstructor
public class CommandDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<CommandToExecute> selectCommandsToExecute() {
        return jdbcTemplate.query(
                """
                        select
                            id,
                            command
                        from infra.command
                        where finished_at is null
                        """,
                (rs, rn) -> new CommandToExecute(
                        rs.getLong("id"),
                        rs.getString("command")
                )
        );
    }

    public void updateExecutedCommand(ExecutedCommand executedCommand) {
        jdbcTemplate.update(
                """
                        update infra.command
                        set started_at = :startedAt, finished_at = current_timestamp, result = :result
                        where id = :id
                        """,
                new MapSqlParameterSource()
                        .addValue("id", executedCommand.id())
                        .addValue("startedAt", executedCommand.startedAt())
                        .addValue("result", executedCommand.result())
        );
    }
}
