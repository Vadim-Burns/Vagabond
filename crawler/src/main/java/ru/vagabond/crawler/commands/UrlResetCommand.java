package ru.vagabond.crawler.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vagabond.crawler.core.command.Command;
import ru.vagabond.crawler.daos.UrlDao;

/**
 * Команда ресета базы на случай, если обнаружена ошибка в работе crawler или indexer.
 * <p>
 * 1.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UrlResetCommand implements Command {

    private static final String COMMAND_NAME = "url-reset";

    private final UrlDao urlDao;

    @Override
    public String execute() {
        return "Resetted %s urls".formatted(
                urlDao.resetUrls()
        );
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
