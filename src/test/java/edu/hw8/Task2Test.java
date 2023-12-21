package edu.hw8;

import edu.hw8.task2.FixedTreadPool;
import edu.hw8.task2.ThreadPool;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Task2Test {
    private static final ConcurrentHashMap<Integer, Long> memory = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("test fibonacci")
    void test() {
        try (ThreadPool threadPool = FixedTreadPool.create(4)) {
            threadPool.start();
            for (int i = 0; i < 10; i++) {
                final int n = i;
                threadPool.execute(() -> {
                    long fib = calculate(n);
                    LOGGER.info("n = " + n + ", fib = " + fib);
                });
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("test stop")
    void test2() {
        ThreadPool threadPool = FixedTreadPool.create(4);
        threadPool.start();
        threadPool.execute(() -> calculate(1));
        threadPool.execute(() -> calculate(2));
        threadPool.execute(() -> calculate(3));
        threadPool.execute(() -> calculate(4));
        assertDoesNotThrow(threadPool::close);

    }

    private static long calculate(int n) {
        if (memory.containsKey(n)) {
            return memory.get(n);
        }
        if (n <= 1) {
            memory.put(n, 1L);
        } else {
            memory.put(n, calculate(n - 1) + calculate(n - 2));
        }
        return memory.get(n);
    }
}
