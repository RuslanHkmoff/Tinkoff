package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MultithreadingCounter {
    private static final int MAX_ITERATE = 1000;
    private static final int DEFAULT_THREADS = 10;
    private final AtomicInteger value;

    private final int numberOfThreads;

    public MultithreadingCounter(int value, int numberOfThreads) {
        this.value = new AtomicInteger(value);
        this.numberOfThreads = numberOfThreads;
    }

    public MultithreadingCounter() {
        value = new AtomicInteger();
        numberOfThreads = DEFAULT_THREADS;
    }

    public void increment() {
        Stream.generate(() -> new Thread(() -> {
                for (int i = 0; i < MAX_ITERATE; ++i) {
                    value.incrementAndGet();
                }
            }))
            .limit(numberOfThreads)
            .forEach(thread -> {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public int getValue() {
        return value.intValue();
    }
}
