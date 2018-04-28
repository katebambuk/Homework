package ru.sberbank.homework.kiseleva.exceptions;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
