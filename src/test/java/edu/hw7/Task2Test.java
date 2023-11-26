package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("test factorial")
    void test() {
        int[] tests = new int[] {0, 3, 6};
        int[] expected = new int[] {1, 6, 720};

        for (int i = 0; i < tests.length; i++) {
            assertThat(Task2.factorial(tests[i])).isEqualTo(expected[i]);
        }
    }
}
