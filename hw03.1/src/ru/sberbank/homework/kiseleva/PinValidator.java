package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class PinValidator {
    private String pin = "1234";

    public boolean validate(String pin) {
        return this.pin.equals(pin);
    }
}
