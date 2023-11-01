package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    @DisplayName("Test valid data")
    void test1() {
        int[] tests = new int[] {1, 12, 3999};
        String[] expected = new String[] {"I", "XII", "MMMCMXCIX"};
        for (int i = 0; i < tests.length; i++) {
            assertThat(convertToRoman(tests[i])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Test invalid data")
    void test2() {
        int test = 4001;
        String expected = "Number must be from 1 to 3999, actual: '" + test + "'";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> convertToRoman(test),
            "Expected IllegalArgumentException, but didn't"
        );
        assertTrue(expected.contains(thrown.getMessage()));
    }
}
