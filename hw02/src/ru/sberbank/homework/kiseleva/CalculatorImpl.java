package ru.sberbank.homework.kiseleva;

import java.text.DecimalFormat;

import ru.sberbank.homework.common.Calculator;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public class CalculatorImpl implements Calculator {

    private static CommonCalculatorImpl commonCalculator = new CommonCalculatorImpl();
    private DecimalFormat df = new DecimalFormat("#.##");
    private Number result;

    @Override
    public String calculate(String userInput) {
        try {
            parse(userInput);
            return df.format(result).replace(",", ".");
        } catch (IllegalArgumentException e) {
            try {
                if (e.getMessage().equals("Cannot format given Object as a Number")) {
                    return "error > wrong expression";
                }
                String[] arr = e.getMessage().split(" ");
                String wrongArgument = arr[arr.length - 1];
                return "error > " + wrongArgument.replace("\"", "");
            } catch (Exception ex) {
                return "error > wrong expression";
            }
        } catch (NullPointerException ex) {
            return "error > wrong expression";
        }
    }


    public void parse(String userInput) {
        String[] parsedArr = userInput.split(" ");

        if (parsedArr.length == 3) { //первый ввод
            computation(parsedArr[0], parsedArr[2], parsedArr[1].charAt(0));
        } else if (parsedArr.length == 2) { //последующий ввод
            computation(String.valueOf(result), parsedArr[1], parsedArr[0].charAt(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void computation(String numOne, String numTwo, char operation) {

        switch (operation) {
            case ('+'):
                result = commonCalculator.sum(commonCalculator.cast(numOne), commonCalculator.cast(numTwo));
                break;
            case ('-'):
                result = commonCalculator.subtract(commonCalculator.cast(numOne), commonCalculator.cast(numTwo));
                break;
            case ('*'):
                result = commonCalculator.multiply(commonCalculator.cast(numOne), commonCalculator.cast(numTwo));
                break;
            case ('/'):
                result = commonCalculator.divide(commonCalculator.cast(numOne), commonCalculator.cast(numTwo));
                break;
        }
    }
}

