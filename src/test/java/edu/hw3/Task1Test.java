package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.atbash;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Testing atbash cipher")
    void test() {
        String[] tests = new String[] {
            "Hello world!",
            "Any fool can write code that a computer can understand.",
            ""
        };
        String[] expected = new String[]{
            "Svool dliow!",
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw.",
            ""
        };
        for (int i = 0; i < tests.length; i++){
            assertThat(atbash(tests[i])).isEqualTo(expected[i]);
        }
    }
}
