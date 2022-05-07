package ru.job4j.testtask;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void createCalculator() {
        calculator = new Calculator();
    }

    @Test
    public void whenFiveMultiplySevenThenThirtyFive() {
        String expression = "5 * 7";
        assertThat(calculator.getResultExpression(expression), is("35"));
    }

    @Test
    public void whenVIDivIIThenIII() {
        String expression = "VI / II";
        assertThat(calculator.getResultExpression(expression), is("III"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenVIAddTwoThenException() {
        String expression = "VI + 2";
        calculator.getResultExpression(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOneAddTwoAddThreeThenException() {
        String expression = "1 + 2 + 3";
        calculator.getResultExpression(expression);
    }

    @Test
    public void whenIIDivIIThenI() {
        String expression = "II / II";
        assertThat(calculator.getResultExpression(expression), is("I"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenISubtractIThenException() {
        String expression = "I - I";
        calculator.getResultExpression(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongExpressionThenException() {
        String expression = "I- I";
        calculator.getResultExpression(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongActionThenException() {
        String expression = "I ^ I";
        calculator.getResultExpression(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongOperandMoreTenException() {
        String expression = "11 + 7";
        calculator.getResultExpression(expression);
    }

    @Test
    public void whenThreeSubtractFiveThenMinusTwo() {
        String expression = "3 - 5";
        assertThat(calculator.getResultExpression(expression), is("-2"));
    }

    @Test
    public void whenThreeDivFiveThenNull() {
        String expression = "3 / 5";
        assertThat(calculator.getResultExpression(expression), is("0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIIIDivVThenException() {
        String expression = "III / V";
        calculator.getResultExpression(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenXIIDivVIThenException() {
        String expression = "XII / IV";
        calculator.getResultExpression(expression);
    }

    @Test
    public void whenXMultipleXThenC() {
        String expression = "X * X";
        assertThat(calculator.getResultExpression(expression), is("C"));
    }

    @Test
    public void whenXAddXThenXX() {
        String expression = "X + X";
        assertThat(calculator.getResultExpression(expression), is("XX"));
    }
}