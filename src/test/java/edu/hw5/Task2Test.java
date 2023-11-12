package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("test getAllFridays13ths")
    void test1() {
        int year = 2023;
        List<LocalDate> expected = List.of(
            LocalDate.of(2023, 1, 13),
            LocalDate.of(2023, 10, 13)
        );
        assertThat(Task2.getAllFridays13ths(year)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getNextFriday13th, in this year")
    void test2() {
        LocalDate date = LocalDate.of(2023, 6, 14);
        LocalDate expected = LocalDate.of(2023, 10, 13);
        assertThat(Task2.getNextFriday13th(date)).isEqualTo(expected);
    }
    @Test
    @DisplayName("test getNextFriday13th, in new year")
    void test3() {
        LocalDate date = LocalDate.of(2022, 12, 30);
        LocalDate expected = LocalDate.of(2023, 1, 13);
        assertThat(Task2.getNextFriday13th(date)).isEqualTo(expected);
    }

}
