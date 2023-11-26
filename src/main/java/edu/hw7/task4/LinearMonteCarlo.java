package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class LinearMonteCarlo {
    private LinearMonteCarlo() {
    }

    public static double calculate(int numberOfSim) {
        double inCircle = 0;
        for (int i = 0; i < numberOfSim; ++i) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (MonteCarloUtils.containsPoint(x, y)) {
                inCircle++;
            }
        }
        return 4.0 * (inCircle / numberOfSim);
    }
}
