package edu.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public Long fib(int number) {
        if (number == 2 || number == 1) {
            return 1L;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
