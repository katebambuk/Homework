package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 07.02.2018.
 */
class Assert {
    private static final double EPSILON = 0.00001;

    public static void equal(String message, int expected, int actual) {
        if (actual == expected) {
            System.out.println(message);
        } else {
            throw new TestFailedException(actual + " is not equal " + expected);
        }
    }

    public static void equal(String message, double expected, double actual) {
        if (Math.abs(expected - actual) < EPSILON) {
            System.out.println(message);
        } else {
            throw new TestFailedException(actual + " is not equal " + expected);
        }
    }

    private static class TestFailedException extends RuntimeException {
        public TestFailedException(String message) {
            super(message);
        }
    }
}
