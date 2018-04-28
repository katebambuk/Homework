package ru.sberbank.homework.kiseleva;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixedThreadPoolTest {
    private int capacity = 2;
    private int limitTasks = 11;
    private FixedThreadPool threadPool = new FixedThreadPool(capacity);

    @Test
    public void startThenExecute() throws InterruptedException {
        threadPool.start();
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        Thread.sleep(150 * limitTasks);
        assertEquals(capacity, threadPool.getThreads().size());
        assertEquals(limitTasks, threadPool.getRunnedTasksCounter().get());
    }

    @Test
    public void executeThenStart() throws InterruptedException {
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        threadPool.start();
        Thread.sleep(150 * limitTasks);
        assertEquals(capacity, threadPool.getThreads().size());
        assertEquals(limitTasks, threadPool.getRunnedTasksCounter().get());
    }

    @Test
    public void shutDownTest() throws InterruptedException {
        threadPool.start();
        threadPool.shutdown();
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        Thread.sleep(150 * limitTasks);
        assertEquals(0, threadPool.getRunnedTasksCounter().get());
    }
}

