package ru.sberbank.homework.kiseleva.exceptions;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class AccountIsLockedException extends RuntimeException {

    public AccountIsLockedException(long l) {
        super(l + " sec to unlock");
    }
}
