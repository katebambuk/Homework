package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        linear();
        parallel();
    }

    private static void linear() {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }

    private static void parallel() throws InterruptedException {
        long millis = System.currentTimeMillis();
        StringsTask stringsTask = new StringsTask();
        Thread stringThread = new Thread(stringsTask);

        SleepyTask sleepyTask = new SleepyTask();
        Thread sleepyThread = new Thread(sleepyTask);

        CalculationTask calculationTask = new CalculationTask();
        Thread calcThread = new Thread(calculationTask);

        stringThread.start();
        calcThread.start();
        sleepyThread.start();

        calcThread.join();
        stringThread.join();
        sleepyThread.join();
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}
