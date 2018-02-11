package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 07.02.2018.
 */
public class SimpleCalculatorTest {
    SimpleCalculator simpleCalculator = new SimpleCalculator();

    public void additionTest(int one, int two, int expected) {
        int actual = simpleCalculator.addition(one, two);
        Assert.equal("Addition int test passed", expected, actual);
    }

    public void subtractionTest(int one, int two, int expected) {
        int actual = simpleCalculator.subtraction(one, two);
        Assert.equal("Subtraction int test passed", expected, actual);
    }

    public void multiplicationTest(int one, int two, int expected) {
        int actual = simpleCalculator.multiplication(one, two);
        Assert.equal("Multiplication int test passed", expected, actual);
    }

    public void divisionTest(int one, int two, int expected) {
        int actual = simpleCalculator.division(one, two);
        Assert.equal("Division int test passed", expected, actual);
    }

    public void additionTest(double one, double two, double expected) {
        double actual = simpleCalculator.addition(one, two);
        Assert.equal("Addition double test passed", expected, actual);
    }

    public void subtractionTest(double one, double two, double expected) {
        double actual = simpleCalculator.subtraction(one, two);
        Assert.equal("Subtraction double test passed", expected, actual);
    }

    public void multiplicationTest(double one, double two, double expected) {
        double actual = simpleCalculator.multiplication(one, two);
        Assert.equal("Multiplication double test passed", expected, actual);
    }

    public void divisionTest(double one, double two, double expected) {
        double actual = simpleCalculator.division(one, two);
        Assert.equal("Division double test passed", expected, actual);
    }
}
