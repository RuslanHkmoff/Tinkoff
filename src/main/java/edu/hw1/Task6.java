package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private static final int MIN = 1000;
    private static final int MAX = 10000;
    private static final int TEN = 10;
    private static final int LENGTH = 4;
    private static final int KAPREKAR_CONST = 6174;
    private static int steps = 0;

    private Task6() {
    }

    public static int countK(int number) {
        if (number <= MIN || number >= MAX) {
            throw new IllegalArgumentException("Expected number > 1000, actual: " + number);
        }
        if (sameDigits(number)) {
            throw new IllegalArgumentException("Expected number with different digits, actual: " + number);

        }
        if (number == KAPREKAR_CONST) {
            int result = steps;
            steps = 0;
            return result;
        }
        steps++;
        int next = getNext(number);
        return countK(next);
    }

    private static boolean sameDigits(int number) {
        int copy = number;
        int previous = copy % TEN;
        while (copy > 0) {
            copy /= TEN;
            if (previous != copy % TEN && copy != 0) {
                return false;
            }
        }
        return true;
    }

    private static int getNext(int number) {
        int[] array = new int[LENGTH];
        int copy = number;
        for (int i = LENGTH - 1; i >= 0; i--) {
            array[i] = copy % TEN;
            copy /= TEN;
        }
        Arrays.sort(array);
        int sorted = 0;
        int reversed = 0;
        for (int i = 0; i < array.length; i++) {
            sorted += (int) (array[i] * Math.pow(TEN, array.length - i - 1));
            reversed += (int) (array[i] * Math.pow(TEN, i));
        }
        int result = reversed - sorted;
        while (result < MIN) {
            result *= TEN;
        }
        return result;
    }
}
