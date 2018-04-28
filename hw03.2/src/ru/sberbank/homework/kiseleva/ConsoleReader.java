package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.Reader;

import java.util.Scanner;

/**
 * Created by Ekaterina Kiseleva on 16.02.2018.
 */
public class ConsoleReader implements Reader {
    @Override
    public String readUrl() {
        System.out.println("please enter URL: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
