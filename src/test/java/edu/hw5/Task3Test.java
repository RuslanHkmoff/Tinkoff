package edu.hw5;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("test parseDate")
    void test() {
        String[] tests = new String[] {
            "2020-10-10",
            "2020-12-2",
            "1/3/1976",
            "1/3/20",
            "tomorrow",
            "today",
            "yesterday",
            "1 day ago",
            "7 days ago",
            "invalid date"
        };
        Optional[] expected = new Optional[] {
            Optional.of(LocalDate.of(2020, 10, 10)),
            Optional.of(LocalDate.of(2020, 12, 2)),
            Optional.of(LocalDate.of(1976, 1, 3)),
            Optional.of(LocalDate.of(2020, 1, 3)),
            Optional.of(LocalDate.of(2023, 11, 13)),
            Optional.of(LocalDate.of(2023, 11, 12)),
            Optional.of(LocalDate.of(2023, 11, 11)),
            Optional.of(LocalDate.of(2023, 11, 11)),
            Optional.of(LocalDate.of(2023, 11, 5)),
            Optional.empty()
        };
        for (int i = 0; i < tests.length; i++) {
            assertThat(DateParser.parseDate(tests[i])).isEqualTo(expected[i]);
        }
    }
}
