package ru.sberbank.homework.common;

/**
 * Исключение на случай, если что-то пойдет не так с извлечением закешированного маршрута.
 */
public class RouteFetchException extends RuntimeException {
    public RouteFetchException(Throwable cause) {
        super("Can't fetch cached route!", cause);
    }
}
