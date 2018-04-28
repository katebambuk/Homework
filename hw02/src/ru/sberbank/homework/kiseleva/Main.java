package ru.sberbank.homework.kiseleva;

import java.util.Scanner;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public class Main {
    static CalculatorImpl calculator = new CalculatorImpl();

    public static void main(String[] args) {
        System.out.println("Please enter <number> <operation> <number> ");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println(calculator.calculate(expression));
            System.out.println("For exit type 'quit' ");
            System.out.println("To continue type <operation> <number> ");
        }
    }
}