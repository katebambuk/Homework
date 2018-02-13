package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 07.02.2018.
 */
public class SimpleCalculatorTest {
    SimpleCalculator simpleCalculator = new SimpleCalculator();

    public void sumTest(int one, int two, int expected) {
        int actual = simpleCalculator.sum(one, two);
        Assert.equal("Addition int test passed", expected, actual);
    }

    public void subtractTest(int one, int two, int expected) {
        int actual = simpleCalculator.subtract(one, two);
        Assert.equal("Subtraction int test passed", expected, actual);
    }

    public void multiplyTest(int one, int two, int expected) {
        int actual = simpleCalculator.multiply(one, two);
        Assert.equal("Multiplication int test passed", expected, actual);
    }

    public void divideTest(int one, int two, int expected) {
        int actual = simpleCalculator.divide(one, two);
        Assert.equal("Division int test passed", expected, actual);
    }

    public void sumTest(double one, double two, double expected) {
        double actual = simpleCalculator.sum(one, two);
        Assert.equal("Addition double test passed", expected, actual);
    }

    public void subtractTest(double one, double two, double expected) {
        double actual = simpleCalculator.subtract(one, two);
        Assert.equal("Subtraction double test passed", expected, actual);
    }

    public void multiplyTest(double one, double two, double expected) {
        double actual = simpleCalculator.multiply(one, two);
        Assert.equal("Multiplication double test passed", expected, actual);
    }

    public void divideTest(double one, double two, double expected) {
        double actual = simpleCalculator.divide(one, two);
        Assert.equal("Division double test passed", expected, actual);
    }
}
