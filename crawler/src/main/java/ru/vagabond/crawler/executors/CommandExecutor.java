package ru.vagabond.crawler.executors;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.vagabond.crawler.core.command.Command;
import ru.vagabond.crawler.core.executor.AbstractExecutor;
import ru.vagabond.crawler.core.executor.Executor;
import ru.vagabond.crawler.daos.CommandDao;
import ru.vagabond.crawler.models.CommandToExecute;
import ru.vagabond.crawler.models.ExecutedCommand;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Executor
@RequiredArgsConstructor
public class CommandExecutor extends AbstractExecutor {

    // Сколько спим, если нечего выполнять
    private static final int SLEEP = 1000 * 60;

    private final CommandDao commandDao;
    private final List<Command> commandList;

    private final Map<String, Command> commands = new HashMap<>();

    @PostConstruct
    public void buildCommandsMap() {
        log.info("Loading {} commands", commandList.size());
        commandList.forEach(
                command -> commands.put(command.getName(), command)
        );
    }

    @Override
    public void execute() {
        while (true) {
            List<CommandToExecute> commandToExecuteList = commandDao.selectCommandsToExecute();
            log.info("Got {} commands to execute", commandToExecuteList.size());

            if (commandToExecuteList.isEmpty()) {
                try {
                    log.info("Sleep for {} minutes", SLEEP / 1000 / 60);
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    break;
                }
            }

            for (CommandToExecute commandToExecute : commandToExecuteList) {
                commandDao.updateExecutedCommand(executeCommand(commandToExecute));
            }
        }
    }

    private ExecutedCommand executeCommand(CommandToExecute commandToExecute) {
        log.info("Executing id = {} command = '{}' command", commandToExecute.id(), commandToExecute.command());
        Date startedAt = new Date();
        try {
            Command command = commands.get(commandToExecute.command());
            if (command == null) {
                throw new IllegalArgumentException("Command '%s' not found".formatted(commandToExecute.command()));
            }

            String commandResult = command.execute();
            log.info("Command execution id = {} finished with result = {}", commandToExecute.id(), commandResult);
            return new ExecutedCommand(commandToExecute.id(), commandResult, startedAt);
        } catch (Exception e) {
            log.error("Error during command execution id = {}", commandToExecute.id(), e);
            return new ExecutedCommand(commandToExecute.id(), e.getMessage(), startedAt);
        }
    }
}
