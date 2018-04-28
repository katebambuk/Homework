package ru.sberbank.homework.kiseleva;

public class Main {
    private static TerminalClient client = new TerminalClient();
    public static void main(String[] args) throws InterruptedException {
        client.start();
    }
}