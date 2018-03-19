package ru.sberbank.homework.common;

public interface ThreadPool {

    void start();

    void execute(Runnable runnable);
}
