package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    @DisplayName("Test countK, valid data")
    void testCountKValidData() {
        int[] tests = new int[] {6621, 1234, 1001, 8999};
        int[] expected = new int[] {5, 3, 4, 5};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task6.countK(tests[i])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Test countK, invalid data")
    void testCountKInvalidData() {
        int moreThanMax = 10000;
        String expected1 = "Expected number > 1000 and < 10000, actual: " + moreThanMax;
        IllegalArgumentException thrown1 = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(moreThanMax),
            "Expected countK to throw IllegalArgumentException, but didn't"
        );
        assertTrue(thrown1.getMessage().contains(expected1));
        int lessThanMin = 999;
        String expected2 = "Expected number > 1000 and < 10000, actual: " + lessThanMin;
        IllegalArgumentException thrown2 = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(lessThanMin),
            "Expected countK to throw IllegalArgumentException, but didn't"
        );
        assertTrue(thrown2.getMessage().contains(expected2));
        int sameDigits = 2222;
        String expected3 = "Expected number with different digits, actual: " + sameDigits;
        IllegalArgumentException thrown3 = assertThrows(
            IllegalArgumentException.class,
            () -> Task6.countK(sameDigits),
            "Expected countK to throw IllegalArgumentException, but didn't"
        );
        assertTrue(thrown3.getMessage().contains(expected3));
    }
}
