package ru.sberbank.jschool.homework.classloaders;

public interface Plugin<T> {
    /**
     * plugin operates on a String array of URLs
     * and prints the result to System.out
     * @param urls - string values operated on
     */
    void run(String[] urls);
}