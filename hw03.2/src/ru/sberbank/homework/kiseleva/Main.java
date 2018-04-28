package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.Reader;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Reader reader = new ConsoleReader();
                PageReader pageReader = new PageReader();
                pageReader.readPage(reader.readUrl());
            } catch (IOException e) {
                System.out.println("URL not found! repeat input");
            }
        }
    }
}