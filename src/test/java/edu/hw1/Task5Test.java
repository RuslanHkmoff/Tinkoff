package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Test is palindrome descendant")
    void testIsPalindromeDescendant() {
        int[] tests = new int[] {11211230, 12, 11, 1, 23336014, -141};
        boolean[] expected = new boolean[] {true, false, true, false, true, false};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task5.isPalindromeDescendant(tests[i])).isEqualTo(expected[i]);
        }
    }
}
