package ru.sberbank.homework.common.entity;

public class TeslaModel3 extends Car {
    private String number;
    private int firmwareVersion;

    public TeslaModel3(String number, int firmwareVersion) {
        this.number = number;
        this.firmwareVersion = firmwareVersion;
    }

}
