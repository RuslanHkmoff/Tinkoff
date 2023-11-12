package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("test numberPlateValidation")
    void test() {
        String[] tests = new String[] {"А123ВЕ777",
            "О777ОО177", "123АВЕ777",
            "А123ВГ77",
            "А123ВЕ7777", null};
        boolean[] expected = new boolean[] {true, true, false, false, false, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task5.numberPlateValidation(tests[i])).isEqualTo(expected[i]);
        }

    }
}
