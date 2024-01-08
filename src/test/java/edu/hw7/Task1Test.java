package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("test counter")
    void test() {
        MultithreadingCounter counter = new MultithreadingCounter(0, 5);
        int expected = 5000;
        counter.increment();
        assertThat(counter.getValue()).isEqualTo(expected);
    }
}
