package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 07.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        SimpleCalculatorTest test = new SimpleCalculatorTest();
        test.sumTest(1, 2, 3);
        test.sumTest(1.1, 2.4, 3.5);
        test.subtractTest(0, 5, -5);
        test.subtractTest(5.4, 8.5, -3.1);
        test.multiplyTest(7, 9, 63);
        test.multiplyTest(-9.1, 3.0, -27.3);
        test.divideTest(-110, 10, -11);
        test.divideTest(33.3, 3, 11.1);
    }
}
