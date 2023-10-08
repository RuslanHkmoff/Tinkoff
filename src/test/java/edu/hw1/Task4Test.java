package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Test fix string")
    void testFixString() {
        String[] tests = new String[] {"123456", "hTsii  s aimex dpus rtni.g", "badce", "a", ""};
        String[] expected = new String[] {"214365", "This is a mixed up string.", "abcde", "a", ""};
        for (int i = 0; i < tests.length; ++i) {
            assertThat(Task4.fixString(tests[i])).isEqualTo(expected[i]);
        }

    }
}
