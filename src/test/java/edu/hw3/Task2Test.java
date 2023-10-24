package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.hw3.Task2.clusterize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    @DisplayName("Test, valid data")
    void test1() {
        String[] tests = new String[] {
            "()()()",
            "((()))",
            "",
            "((())())(()(()()))"
        };
        List<List<String>> expected = List.of(
            List.of("()", "()", "()"),
            List.of("((()))"),
            List.of(),
            List.of("((())())", "(()(()()))")
        );
        for (int i = 0; i < tests.length; i++) {
            assertThat(clusterize(tests[i])).isEqualTo(expected.get(i));
        }
    }

    @Test
    @DisplayName("Test, invalid data")
    void test2() {
        String test1 = "(()";
        String test2 = "))((";
        String expected = "Invalid brackets sequences";
        IllegalArgumentException thrown1 = assertThrows(
            IllegalArgumentException.class,
            () -> clusterize(test1),
            "Expected IllegalArgumentException, but didn't"
        );
        assertTrue(expected.contains(thrown1.getMessage()));

        IllegalArgumentException thrown2 = assertThrows(
            IllegalArgumentException.class,
            () -> clusterize(test2),
            "Expected IllegalArgumentException, but didn't"
        );
        assertTrue(expected.contains(thrown2.getMessage()));

    }
}
