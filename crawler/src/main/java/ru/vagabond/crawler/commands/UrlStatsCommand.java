package ru.vagabond.crawler.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vagabond.crawler.core.command.Command;
import ru.vagabond.crawler.daos.UrlDao;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlStatsCommand implements Command {

    private static final String COMMAND_NAME = "url-stats";

    private final UrlDao urlDao;

    @Override
    public String execute() {
        return urlDao.getUrlStats();
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
