package edu.hw10.task2;

import edu.hw10.task2.cache.Cache;

public interface FibCalculator {
    @Cache(persist = true)
     Long fib(int number);
}
