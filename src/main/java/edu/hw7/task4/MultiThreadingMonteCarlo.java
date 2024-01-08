package edu.hw7.task4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class MultiThreadingMonteCarlo {
    private MultiThreadingMonteCarlo() {
    }

    public static double calculate(int total) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        int simulationsPerThread = total / 4;
        int[] circleCounts = new int[4];
        try {
            for (int i = 0; i < 4; i++) {
                final int threadIndex = i;
                Future<Integer> future = executorService.submit(() -> simulate(simulationsPerThread, threadIndex));
                circleCounts[i] = future.get();
            }
        } finally {
            executorService.shutdown();
        }

        int totalCircleCount = 0;
        for (int count : circleCounts) {
            totalCircleCount += count;
        }

        return 4.0 * totalCircleCount / total;
    }

    private static int simulate(int numSimulations, int threadIndex) {
        int circleCount = 0;

        double startX = 0.0;
        double startY = 0.0;
        double endX = 1.0;
        double endY = 1.0;

        switch (threadIndex) {
            case 0 -> {
                endX = 0.5;
                endY = 0.5;
            }
            case 1 -> {
                startX = 0.5;
                endY = 0.5;
            }
            case 2 -> {
                startY = 0.5;
                endX = 0.5;
            }
            default -> {
                startX = 0.5;
                startY = 0.5;
            }
        }

        for (int i = 0; i < numSimulations; i++) {
            double x = ThreadLocalRandom.current().nextDouble(startX, endX);
            double y = ThreadLocalRandom.current().nextDouble(startY, endY);

            if (MonteCarloUtils.containsPoint(x, y)) {
                circleCount++;
            }
        }

        return circleCount;
    }
}
