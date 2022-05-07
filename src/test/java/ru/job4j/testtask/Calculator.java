package ru.job4j.testtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Calculator {
    private int firstNum;
    private int secondNum;
    boolean isRomanNumbers;
    private final Map<String, Integer> dataNumber =
            Map.of(
                    "I", 1,
                    "II", 2,
                    "III", 3,
                    "IV", 4,
                    "V", 5,
                    "VI", 6,
                    "VII", 7,
                    "VIII", 8,
                    "IX", 9,
                    "X", 10
            );

    private final Map<String, Integer> romanNumeral =
            Map.of(
                    "I", 1,
                    "IV", 4,
                    "V", 5,
                    "IX", 9,
                    "X", 10,
                    "XL", 40,
                    "L", 50,
                    "XC", 90,
                    "C", 100
            );

    private final Map<String, BiFunction<Integer, Integer, Integer>> expressionsMap =
            Map.of(
                    "+", Integer::sum,
                    "-", (a, b) -> (a - b),
                    "*", (a, b) -> (a * b),
                    "/", (a, b) -> (a / b)
            );

    public String getRomanNumeral(int number) {
        StringBuilder result = new StringBuilder();
        List<Map.Entry<String, Integer>> listRoman = romanNumeral.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());
        int ost = number;
        int index = 0;
        int val;
        Map.Entry<String, Integer> numeral;
        while (ost != 0) {
            numeral = listRoman.get(index);
            val = numeral.getValue();
            if (val <= ost) {
                result.append(numeral.getKey());
                ost -= val;
            } else {
                index++;
            }
        }
        return result.toString();
    }

    private void validateData(String[] operands) {
        if (operands.length != 3) {
            throw new IllegalArgumentException("Строка не соответствуюет "
                    + "арифметическим операциям.");
        }
        if (!expressionsMap.containsKey(operands[1])) {
            throw new IllegalArgumentException(format("Действие %s нельзя применять.", operands[1]));
        }

        if (isNumeric(operands[0]) && isNumeric(operands[2])) {
            firstNum = Integer.parseInt(operands[0]);
            secondNum = Integer.parseInt(operands[2]);
            isRomanNumbers = false;
            if ((firstNum > 10 || firstNum < 1) || (secondNum > 10 || secondNum < 1)) {
                throw new IllegalArgumentException(format("Неподходящие числа. "
                                + "Выход за пределы диапазона от 1 до 10: %s - %s",
                        operands[0], operands[2]));
            }
        } else if (!(isNumeric(operands[0]) && isNumeric(operands[2]))) {
            if (!dataNumber.containsKey(operands[0]) || !dataNumber.containsKey(operands[2])) {
                throw new IllegalArgumentException(format("Неподходящие числа. "
                                + "Значения операндов недопустимы: %s - %s",
                        operands[0], operands[2]));
            }
            firstNum = dataNumber.get(operands[0]);
            secondNum = dataNumber.get(operands[2]);
            isRomanNumbers = true;
            if (firstNum <= secondNum && "-".equals(operands[1])) {
                throw new IllegalArgumentException(format("Для римских чисел первое число меньше "
                                + "или равно второму при вычислении их разности: %s <= %s",
                        operands[0], operands[2]));
            }
            if (firstNum < secondNum && "/".equals(operands[1])) {
                throw new IllegalArgumentException(format("Для римских чисел первое число меньше "
                                + "второго при вычислении их частного: %s < %s",
                        operands[0], operands[2]));
            }
        } else {
            throw new IllegalArgumentException(format("Типы цифр различны: %s - %s",
                    operands[0], operands[1]));
        }

    }

    private boolean isNumeric(String str) {
        boolean result = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public String getResultExpression(String expression) {
        String[] operands = expression.split(" ");
        validateData(operands);
        int resultExpression = expressionsMap.get(operands[1]).apply(firstNum, secondNum);
        return (isRomanNumbers) ? getRomanNumeral(resultExpression) : String.valueOf(resultExpression);
    }

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (!"exit".equals(line)) {
            System.out.println(calculator.getResultExpression(line));
            line = reader.readLine();
        }
    }
}

