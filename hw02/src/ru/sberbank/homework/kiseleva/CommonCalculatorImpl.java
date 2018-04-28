package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.CommonCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class CommonCalculatorImpl implements CommonCalculator {
    @Override
    public Double sum(Number one, Number two) {
        return one.doubleValue() + two.doubleValue();
    }

    @Override
    public Double subtract(Number one, Number two) {
        return one.doubleValue() - two.doubleValue();
    }

    @Override
    public Double multiply(Number one, Number two) {
        return one.doubleValue() * two.doubleValue();
    }

    @Override
    public Double divide(Number one, Number two) {
        return one.doubleValue() / two.doubleValue();
    }


    @Override
    public Number cast(String originalNum) {
        String lowerCaseNum = originalNum.toLowerCase();
        try {
            if (lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'l') { //L
                lowerCaseNum = lowerCaseNum.substring(0, lowerCaseNum.length() - 1);
            }

            if (checkingDelimiter(lowerCaseNum)) {
                lowerCaseNum = lowerCaseNum.replaceAll("_", "");
            }

            if (checkWithRegExp(lowerCaseNum)) {

                if (lowerCaseNum.charAt(0) == '+') {
                    lowerCaseNum = lowerCaseNum.replace("+", "");
                }

                if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(1) == 'b') {
                    return Long.parseLong(lowerCaseNum.substring(2), 2);
                } else if (lowerCaseNum.contains("-0b")) {
                    return -Long.parseLong(lowerCaseNum.substring(3), 2);
                } else if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(1) == 'x') {
                    return Long.parseLong(lowerCaseNum.substring(2), 16);
                } else if (lowerCaseNum.contains("-0x")) {
                    return -Long.parseLong(lowerCaseNum.substring(3), 16);
                } else if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(0) == '0') {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    return Long.parseLong(lowerCaseNum, 8);
                } else {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    return Long.parseLong(lowerCaseNum);
                }
            } else { //вещественный литерал
                if (lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'f' || lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'd') {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    lowerCaseNum = lowerCaseNum.substring(0, lowerCaseNum.length() - 1);
                }
                return Double.parseDouble(lowerCaseNum);
            }
        } catch (Exception e) {
            if (originalNum.equals("null")) {
                throw new NullPointerException();
            }
            throw new IllegalArgumentException(originalNum);
        }
    }

    public boolean checkWithRegExp(String num) {
        Pattern p = Pattern.compile("^[-+]?0([0-9]*)?(b.*)?(x.*)?( )?$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    public boolean checkingDelimiter(String num) {
        if (num.charAt(0) == '_') {
            return false;
        } else if (num.contains("0x_") || num.contains("0b_")) {
            return false;
        } else if (num.contains("._") || num.contains("_.")) {
            return false;
        } else if (num.lastIndexOf('_') == num.length() - 1) {
            return false;
        } else {
            return true;
        }
    }
}
