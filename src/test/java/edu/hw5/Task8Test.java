package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("test oddLength")
    void test1() {
        String[] tests = new String[] {"11", "1", "101", "1010", "11001"};
        boolean[] expected = new boolean[] {false, true, true, false, true};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task8.oddLength(tests[i])).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("test zeroOddLengthOrOneEvenLength")
    void test2() {
        String[] tests = new String[] {"0", "1", "001", "1010", "11001"};
        boolean[] expected = new boolean[] {true, false, true, true, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task8.zeroOddLengthOrOneEvenLength(tests[i])).isEqualTo(expected[i]);
        }
    }
    @Test
    @DisplayName("test zerosDivisibleByThree")
    void test3() {
        String[] tests = new String[] {"0", "101", "10001", "1010100", "1000101001"};
        boolean[] expected = new boolean[] {false, false, true, false, true};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task8.zerosDivisibleByThree(tests[i])).isEqualTo(expected[i]);
        }
    }
    @Test
    @DisplayName("test without11and111")
    void test4() {
        String[] tests = new String[] {"0", "1", "001", "111", "11"};
        boolean[] expected = new boolean[] {true, true, true, false, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task8.without11and111(tests[i])).isEqualTo(expected[i]);
        }
    }
}
