package edu.hw7;

import edu.hw7.task4.LinearMonteCarlo;
import edu.hw7.task4.MultiThreadingMonteCarlo;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    @DisplayName("test speed")
    void test() {
        long start = System.currentTimeMillis();
        try {
            MultiThreadingMonteCarlo.calculate(100_000_000);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long middle = System.currentTimeMillis();
        LinearMonteCarlo.calculate(100_000_000);
        long end = System.currentTimeMillis();
        assertTrue((middle - start) <= (end - middle));
    }
}
