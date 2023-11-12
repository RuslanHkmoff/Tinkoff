package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("test atLeastThreeThirdZero")
    void test1() {
        String[] tests = new String[] {"1", "101", "100", "0000", "1011", "0101101"};
        boolean[] expected = new boolean[] {false, false, true, true, false, true};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task7.atLeastThreeThirdZero(tests[i])).isEqualTo(expected[i]);
        }
    }
    @Test
    @DisplayName("test startAndEndEqual")
    void test2() {
        String[] tests = new String[] {"1", "0", "10", "00", "11", "0101"};
        boolean[] expected = new boolean[] {true, true, false, true, true, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task7.startAndEndEqual(tests[i])).isEqualTo(expected[i]);
        }
    }
    @Test
    @DisplayName("test atLeast1AtMost3")
    void test3() {
        String[] tests = new String[] {"1", "", "10", "010", "10101", "0101"};
        boolean[] expected = new boolean[] {true, false, true, true, false, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task7.atLeast1AtMost3(tests[i])).isEqualTo(expected[i]);
        }
    }
}
