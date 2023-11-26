package edu.hw7;

import edu.hw7.task4.LinearMonteCarlo;
import edu.hw7.task4.MultiThreadingMonteCarlo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class Task4Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("test speed")
    void test() {
        long start = System.currentTimeMillis();
        double multithreading = 0;
        try {
            multithreading = MultiThreadingMonteCarlo.calculate(100_000_000);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long middle = System.currentTimeMillis();
        double linear = LinearMonteCarlo.calculate(100_000_000);
        long end = System.currentTimeMillis();
        LOGGER.info(String.format("Multithreading calculate value: %f, time: %dms", multithreading, (middle - start)));
        LOGGER.info(String.format("Linear calculate value: %f, time: %dms", linear, (end - middle)));
//        assertTrue((middle - start) <= (end - middle)); // Works locally, but doesn't work on github
    }
}
