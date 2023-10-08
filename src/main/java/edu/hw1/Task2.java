package edu.hw1;

public class Task2 {
    private static final int BASE = 10;

    private Task2() {
    }

    public static int countDigits(int number) {
        int copy = number;
        int digits = 0;
        do {
            digits++;
            copy /= BASE;
        } while (copy != 0);
        return digits;
    }

}
