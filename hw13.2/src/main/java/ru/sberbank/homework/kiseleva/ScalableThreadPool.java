package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.common.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool {
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;
    private List<Thread> threads = new ArrayList<>();
    private volatile AtomicInteger runnedTasksCounter = new AtomicInteger();
    private int minPoolSize;
    private int maxPoolSize;

    ScalableThreadPool(int min, int max) {
        this.minPoolSize = min;
        this.maxPoolSize = max;
        for (int i = 0; i < min; i++) {
            threads.add(new Thread(new Worker()));
        }
        new Thread(new ThreadCleaner()).start();
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public AtomicInteger getRunnedTasksCounter() {
        return runnedTasksCounter;
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            taskQueue.offer(runnable);
            if (taskQueue.size() > minPoolSize && taskQueue.size() <= maxPoolSize) {
                Thread thread = new Thread(new Worker());
                threads.add(thread);
            }
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    class Task implements Runnable {
        @Override
        public void run() {
            runnedTasksCounter.getAndIncrement();
        }
    }

    public Runnable getTask() {
        return new Task();
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!taskQueue.isEmpty() && isRunning) {
                Runnable nextTask = taskQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }

    private class ThreadCleaner implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                while (threads.size() > minPoolSize && taskQueue.size() < threads.size()) {
                    threads.get(threads.size() - 1).interrupt();
                    threads.remove(threads.size() - 1);
                }
            }
        }
    }
}
