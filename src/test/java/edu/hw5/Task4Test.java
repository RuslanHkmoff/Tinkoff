package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("test password validation")
    void test1() {
        String[] tests = new String[] {"abacaba", "a@1", "@@", "~!@#$%^&*|", null, ""};
        boolean[] expected = new boolean[] {false, true, true, true, false, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task4.passwordValidation(tests[i])).isEqualTo(expected[i]);
        }
    }

}
