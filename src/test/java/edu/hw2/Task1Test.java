package edu.hw2;

import edu.hw2.Task1.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Testing constant")
    void test1() {
        Constant constant = new Constant(2);
        double expectedValue = 2.0;
        String expetedString = Double.toString(expectedValue);
        assertThat(constant.evaluate()).isEqualTo(expectedValue);
        assertThat(constant.toString()).isEqualTo(expetedString);
    }

    @Test
    @DisplayName("Testing negate")
    void test2() {
        Constant constant = new Constant(2);
        Expression expression = new Negate(constant);
        double expectedValue = -2.0;
        String expetedString = "(-2.0)";
        assertThat(expression.evaluate()).isEqualTo(expectedValue);
        assertThat(expression.toString()).isEqualTo(expetedString);
    }

    @Test
    @DisplayName("Testing exponent")
    void test3() {
        Expression expression = new Exponent(new Constant(2), new Constant(3));
        double expectedValue = 8.0;
        String expetedString = "(2.0 ** 3.0)";
        assertThat(expression.evaluate()).isEqualTo(expectedValue);
        assertThat(expression.toString()).isEqualTo(expetedString);
    }

    @Test
    @DisplayName("Testing addition")
    void test4() {
        Expression expression = new Addition(new Constant(2), new Constant(5));
        double expectedValue = 7.0;
        String expetedString = "(2.0 + 5.0)";
        assertThat(expression.evaluate()).isEqualTo(expectedValue);
        assertThat(expression.toString()).isEqualTo(expetedString);
    }
    @Test
    @DisplayName("Testing multiplication")
    void test5() {
        Expression expression = new Multiplication(new Constant(2), new Constant(5));
        double expectedValue = 10.0;
        String expetedString = "(2.0 * 5.0)";
        assertThat(expression.evaluate()).isEqualTo(expectedValue);
        assertThat(expression.toString()).isEqualTo(expetedString);
    }
}
