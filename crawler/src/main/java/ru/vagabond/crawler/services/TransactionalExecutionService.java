package ru.vagabond.crawler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalExecutionService {

    private final Queue<Callable<Void>> executeQueue = new ConcurrentLinkedQueue<>();

    public void addCallableToExecute(Callable<Void> toExecute) {
        executeQueue.add(toExecute);
    }

    public Callable<Void> pollCallableToExecute() {
        return executeQueue.poll();
    }
}
