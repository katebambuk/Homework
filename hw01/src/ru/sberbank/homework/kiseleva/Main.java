package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 07.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        SimpleCalculatorTest test = new SimpleCalculatorTest();
        test.additionTest(1, 2, 3);
        test.additionTest(1.1, 2.4, 3.5);
        test.subtractionTest(0, 5, -5);
        test.subtractionTest(5.4, 8.5, -3.1);
        test.multiplicationTest(7, 9, 63);
        test.multiplicationTest(-9.1, 3.0, -27.3);
        test.divisionTest(-110, 10, -11);
        test.divisionTest(33.3, 3, 11.1);
    }
}
