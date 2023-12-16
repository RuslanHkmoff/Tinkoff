package edu.hw10;

import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FibCalculator;
import edu.hw10.task2.FibCalculatorImpl;
import edu.hw10.testClasses.PowCalculator;
import edu.hw10.testClasses.PowCalculatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CacheProxyTest {
    @Test
    @DisplayName("test persist=true")
    void test1() throws IOException {
        FibCalculator c = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);
        assertDoesNotThrow(() -> proxy.fib(1));
        Path path = Path.of("src/main/resources/cache/fib#[1]");
        assertTrue(Files.exists(path));
        String expected = "1";
        assertThat(Files.readString(path)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test persist=false")
    void test2() throws IOException {
        PowCalculator c = new PowCalculatorImpl();
        PowCalculator proxy = CacheProxy.create(c, PowCalculator.class);
        assertThat(proxy.pow(2)).isEqualTo(4);
        Path path = Path.of("src/main/resources/cache/pow#[2]");
        assertFalse(Files.exists(path));
    }
}
