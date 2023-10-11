package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Test rotate left")
    void testRotateLeft() {
        int[][] tests = new int[][] {
            {16, 1},
            {17, 2},
            {2, 0},
            {0, 5},
            {-7, 3},
            {2147483640, 2}
        };
        int[] expected = new int[] {1, 6, 2, 0, -7, 2147483619};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task7.rotateLeft(tests[i][0], tests[i][1])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Test rotate right")
    void testRotateRight() {
        int[][] tests = new int[][] {
            {8, 1},
            {0, 2},
            {-7, 2},
            {17, 2},
            {0, 5},
            {2147483640, 2}
        };
        int[] expected = new int[] {4, 0, -7, 12, 0, 536870910};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task7.rotateRight(tests[i][0], tests[i][1])).isEqualTo(expected[i]);
        }
    }
}
