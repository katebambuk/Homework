package ru.sberbank.homework.checker;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.homework.common.Calculator;

import java.util.Arrays;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
public class SolutionChecker {
    private static final String WRONG_MSG = "error > wrong expression";
    private final String testedPackage;

    private final CommandSequence[] sequences = {
            cs("Простые примеры. happy path all operations",
                    ex("2 + 2", "4"), ex("- 1", "3"), ex("* 2", "6"), ex("/ 4", "1.5"), ex("1 - 1", "0")),
            cs("Неаккуратный пользователь",
                    ex("1+1", WRONG_MSG), ex("1 - foo", "error > foo")),
            cs("Бинарные литералы",
                    ex("0b01110111 + 0b00000001", "120"), ex("- 0b0000_0110", "114"), ex("/ 0B110", "19"), ex("* 0.5", "9.5"),
                    ex("0b11111111_11111111_11111111_11111111_11111111L + 2", "1099511627777")),
            cs("Суффиксы, разряды, 0x префикс",
                    ex("10L * 2.5", "25"), ex("20l - 0xf5" /*LONG ;)*/, "-225"), ex("4_2L / 2", "21"), ex("0xFF_ee + 11", "65529"), ex("0b010L + 0X10l", "18")),
            cs("Восьмиричные литералы",
                    ex("0777 + 0777", "1022"), ex("/ 027", "44.43"), ex("/ 0_4", "11.11"), ex("1 + 1", "2"), ex("/ +0_2L", "1")),
            cs("Просто разделители разрядов в разных комбинациях",
                    ex("0_7_7_7 + 0__7__7__7", "1022"), ex("/ 0b0__000_0110", "170.33"), ex("/ 0___4", "42.58")),
            cs("Ошибки в литералах",
                    ex("08 + 2", "error > 08"), ex("0b_100__0 - 2", "error > 0b_100__0"), ex("1010b010 + 8", "error > 1010b010"),
                    ex("0_.01 + 2", "error > 0_.01"), ex("_100 - 2", "error > _100"), ex("1 + 5_L", "error > 5_L"), ex("1 + 0x_FfF", "error > 0x_FfF"),
                    ex("00x5f5 - 2", "error > 00x5f5"), ex("_11 + 2", "error > _11")),
            cs("Ошибки в выражениях",
                    ex("+ 2", WRONG_MSG), ex("0.21 -1_1", WRONG_MSG), ex("+ 2", WRONG_MSG), ex("+00.01 - + +2", WRONG_MSG)),
            cs("Различные комбинации",
                    ex("-0.21 * -1_1", "2.31"), ex("-4_2L / +2", "-21"), ex("-0b010L + -0X10l", "-18"),
                    ex("-1 + +1", "0"), ex("+0x0 + -0x0", "0"), ex("+0b0000 - -0b0000", "0")),
            //Hardcore
            cs("Дополнительно: Integer.parseInt (Long.parseLong) overflow. При записи не в 10 СС забыли про знаковый бит",
                    ex("0b11111111_11111111_11111111_11111111_11111111_11111111_11111111_11111111L + 2", "1"),
                    ex("0b1000_0000_0000_0000_0000_0000_0000_0001 + -0x8000_0000", "1"),
                    ex("037777777773 / 2", "-2"),
                    ex("0xFFFF_FFFF * 4", "-4"),
                    ex("0b1000_0000_0000_0000_0000_0000_0000_0001 + 2", "-2147483645")),

            cs("Дополнительно: более 1 операции в выражении",
                    ex("1 + 0b101 + 0201 * 4 / 2.01 - 1 + 7.2 / 0xFeF + 10L", "271.72")),

            cs("Дополнительно: поддержка скобок",
                    ex("1 + (0b101 + 0201) * (4 / 2.01) - ((1 + 7.2) / 0xFeF) + 10L", "277.66")),

            cs("Дополнительно: ошибки со скобками",
                    ex("1 + 2 ( 2 )", WRONG_MSG), ex("( 1 + 2 ) )", WRONG_MSG), ex("( 1 + 2 ) (", WRONG_MSG),
                    ex("(1 + 2 (", WRONG_MSG), ex(") ) 2 / 2 * 0 ( (", WRONG_MSG), ex("( 2 ( + 3 ) )", WRONG_MSG),
                    ex("1 ( + 2 )", WRONG_MSG)),

            cs("Дополнительно: поддержка всех литералов для вещественных типов",
                    ex("23.404 + 1.34e-4", "23.40"), ex("/ -3.4f", "-6.88"), ex("+ +0xCAFE__BABEp-2", "851422888.62"),
                    ex("- -0xDEADBEEF", "292384151.62")),
            cs("Дополнительно: исключение ошибочных литералов для вещественных типов",
                    ex("23.40.4 + 1.34e-4", "error > 23.40.4"), ex("2 / -3.4_f", "error > -3.4_f"),
                    ex("5 + +0xCAFEpBABE-2", "error > +0xCAFEpBABE-2"), ex("0.2d * 0.fe3", "error > 0.fe3"))

    };

    public void startTesting(Supplier<Calculator> supplier) {
        for (CommandSequence cs : sequences) {
            Calculator calculator = supplier.get();
            log.info("============================== Starting sequence {} ============================== ", cs.getDescription());
            cs.getExpressions().forEach(expression -> calculateAndCheck(calculator, expression));
        }
    }

    private void calculateAndCheck(Calculator calculator, Expression expression) {
        log.debug("executing {} ", expression.getInput());
        try {
            String result = calculator.calculate(expression.getInput());
            if (expression.getExpectedOutput().equalsIgnoreCase(result)) {
                log.debug("OK: {} = {} ", expression.getInput(), result);
            } else {
                expression.fail();
                log.error("FAIL! input: {} actual: {} expecting: {}", expression.getInput(), result, expression.getExpectedOutput());
            }
        } catch (Throwable th) {
            expression.fail();
            log.error("AN EXCEPTION WAS CAUGHT! {} ", th);
        }
    }

    private CommandSequence cs(String description, Expression... expressions) {
        return CommandSequence.create().addAllExpressions(expressions).addDescription(description);
    }

    private Expression ex(String input, String expectedResult) {
        return new Expression(input, expectedResult);
    }

    public String getStatus() {
        StringBuilder sb = new StringBuilder("Results for package " + testedPackage + ": \n");
        Arrays.stream(sequences).forEach(cs -> sb.append(cs.getDescription()).append(cs.isFailed() ? " FAILED\n" : " PASSED\n"));
        return sb.toString();
    }
}
