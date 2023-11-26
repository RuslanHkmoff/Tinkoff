package edu.hw7.task4;

public class MonteCarloUtils {
    private static final double RADIUS = 0.5;

    private MonteCarloUtils() {
    }

    public static boolean containsPoint(double x, double y) {
        return (x - RADIUS) * (x - RADIUS) + (y - RADIUS) * (y - RADIUS) <= RADIUS * RADIUS;
    }
}
