package ru.sberbank.homework.kiseleva;

/**
 * класс-перечисление операций
 */
public enum Operation {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char operation;

    Operation(char operation) {
        this.operation = operation;
    }

    public char getOperation() {
        return operation;
    }
}
