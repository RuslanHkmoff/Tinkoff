package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task3.freqDict;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Test freqDict")
    void test() {
        List<String> test1 = List.of("aa", "bb", "aa");
        Map<String, Integer> expected1 = Map.of("aa", 2,
            "bb", 1
        );
        assertThat(freqDict(test1)).isEqualTo(expected1);
        List<Integer> test2 = List.of(2, 2, 2, 1, 0);
        Map<Integer, Integer> expected2 = Map.of(2, 3,
            1, 1,
            0, 1
        );
        assertThat(freqDict(test2)).isEqualTo(expected2);

    }
}
