package edu.hw2.Task3;

import java.util.Random;

public class ConnectionUtils {
    private static final int BOUND = 100;

    private static final int MODULE = 100;

    private static final Random RANDOM = new Random();

    private ConnectionUtils() {
    }

    public static boolean getProbabilityFault() {
        int randomNumber = RANDOM.nextInt(BOUND) + 1;
        return randomNumber % MODULE == 0;
    }

    public static boolean getProbabilityFault(boolean want) {
        return want;
    }
}
