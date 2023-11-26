package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {
    private Task2() {
    }

    public static long factorial(int n) {
        return IntStream.rangeClosed(1, n)
            .boxed()
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
