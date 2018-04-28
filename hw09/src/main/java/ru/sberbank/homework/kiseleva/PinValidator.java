package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class PinValidator {
    private String pin = "";

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean validate(String pin) {
        return this.pin.equals(pin);
    }
}
