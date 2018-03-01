package ru.sberbank.homework.common.entity;


import ru.sberbank.homework.common.annotation.ExperimentalFeature;
import ru.sberbank.homework.common.annotation.Prototype;

@Prototype(version = 9001)
public class TeslaModelHydro extends Car {
    private String number;
    private int firmwareVersion;

    @ExperimentalFeature
    private String codename = "Poseidon";

    public TeslaModelHydro(String number, int firmwareVersion) {
        this.number = number;
        this.firmwareVersion = firmwareVersion;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(int firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    @ExperimentalFeature
    public void fuelWithWater(int liters) {

    }


}
