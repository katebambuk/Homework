package ru.sberbank.homework.kiseleva;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScalableThreadPoolTest {
    private int min = 2;
    private int max = 4;
    private ScalableThreadPool threadPool = new ScalableThreadPool(min, max);

    @Before
    public void before() {
        threadPool.getRunnedTasksCounter().set(0);
    }

    @Test
    public void startWithMinTasks() throws InterruptedException {
        int limitTasks = min;
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        threadPool.start();
        Thread.sleep(150 * limitTasks);
        assertEquals(min, threadPool.getThreads().size());
    }

    @Test
    public void startWithMoreThanMaxTasks() throws InterruptedException {
        int limitTasks = max + 3;
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        threadPool.start();
        assertEquals(max, threadPool.getThreads().size());
        Thread.sleep(150 * limitTasks);
        assertEquals(limitTasks, threadPool.getRunnedTasksCounter().get());
    }

    @Test
    public void startWithAverageTasks() throws InterruptedException {
        int limitTasks = (min + max) / 2;
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        threadPool.start();
        assertEquals(limitTasks, threadPool.getThreads().size());
        Thread.sleep(150 * limitTasks);
        assertEquals(limitTasks, threadPool.getRunnedTasksCounter().get());
    }

    @Test
    public void reduceAmountOfThreads() throws InterruptedException {
        int limitTasks = max + 3;
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        threadPool.start();
        while ((threadPool.getRunnedTasksCounter().get() != limitTasks)) {
            Thread.sleep(50 * limitTasks);
        }
        Thread.sleep(150);
        assertEquals(min, threadPool.getThreads().size());
        assertEquals(limitTasks, threadPool.getRunnedTasksCounter().get());
    }

    @Test
    public void shutDownTest() throws InterruptedException {
        threadPool.start();
        threadPool.shutdown();
        int limitTasks = min * 2;
        for (int i = 0; i < limitTasks; i++) {
            threadPool.execute(threadPool.getTask());
        }
        Thread.sleep(150 * limitTasks);
        assertEquals(0, threadPool.getRunnedTasksCounter().get());
    }
}