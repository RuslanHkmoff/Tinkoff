package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Minutes to seconds, valid data")
    void testMinutesToSecondsValidData() {
        String[] tests = new String[] {
            "00:00",
            "23:59",
            "13:56",
            "999:59",
        };
        int[] expected = new int[] {0, 1439, 836, 59999};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task1.minutesToSeconds(tests[i])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Minutes to seconds, invalid data")
    void testMinutesToSecondsInvalidData() {
        String[] tests = new String[] {
            "10:60",
            "-1:00",
            "2131",
            "12:ab",
        };
        int[] expected = new int[] {-1, -1, -1, -1, -1};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task1.minutesToSeconds(tests[i])).isEqualTo(expected[i]);
        }
    }

}
