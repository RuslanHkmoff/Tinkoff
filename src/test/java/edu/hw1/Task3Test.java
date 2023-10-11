package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Task3Test {
    @Test
    @DisplayName("Test is nestable, valid data")
    void testIsNestableValidData() {
        int[][][] tests = new int[][][] {
            {{1, 2, 3, 4}, {0, 6}},
            {{}, {1, 5}},
            {{9, 9, 8}, {8, 9}},
            {{1, 2, 3, 4}, {2, 3}}
        };
        boolean[] expected = new boolean[] {true, true, false, false};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task3.isNestable(tests[i][0], tests[i][1])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Test is nestable, invalid data")
    void testIsNestableInvalidData() {
        int[] arr1 = new int[] {1, 4, 3};
        int[] arr2 = null;
        String expected = "Expected not null value";
        try {
            Task3.isNestable(arr1, arr2);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo(expected);
        }
    }
}
