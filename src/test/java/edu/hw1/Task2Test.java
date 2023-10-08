package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test count digits")
    void testCountDigits() {
        int[] tests = new int[] {
            0,
            10,
            9999,
            -125
        };
        int[] expected = new int[] {1, 2, 4, 3};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task2.countDigits(tests[i])).isEqualTo(expected[i]);
        }
    }
}
