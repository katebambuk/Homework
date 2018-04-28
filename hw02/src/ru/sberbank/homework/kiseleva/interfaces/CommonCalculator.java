package ru.sberbank.homework.kiseleva.interfaces;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public interface CommonCalculator {

    public Number sum(Number one, Number two);

    public Number subtract(Number one, Number two);

    public Number multiply(Number one, Number two);

    public Number divide(Number one, Number two);

    public Number cast(String num) throws Exception;
}
