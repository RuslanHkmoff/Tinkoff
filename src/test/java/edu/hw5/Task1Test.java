package edu.hw5;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    @Test
    @DisplayName("Test with valid data")
    void test1() {
        List<String> test = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        Duration expected = Duration.ofSeconds(13200);
        assertThat(Task1.averageTimeIn(test)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with invalid data")
    void test2() {
        List<String> test = List.of(
            "2022-03-12, 20: - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task1.averageTimeIn(test),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "Session should take form: yyyy-MM-dd, HH:mm - yyyy-MM-dd, HH:mm";
        assertTrue(expected.contains(thrown.getMessage()));
    }
}
