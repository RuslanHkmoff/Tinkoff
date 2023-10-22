package edu.hw2.Task3.connections;

import edu.hw2.Task3.excpetions.ConnectionException;

public class ConnectionGenerator implements Generator {
    private final double faultyProbability;
    private static final double DEFAULT_PROBABILITY = 0.5;

    public ConnectionGenerator(double faultyProbability) {
        this.faultyProbability = faultyProbability;
    }

    public Connection generateConnection() {
        if (Math.random() < faultyProbability) {
            return new FaultyConnection(this);
        }
        return new StableConnection();
    }

    public void generateException() {
        if (Math.random() < DEFAULT_PROBABILITY) {
            throw new ConnectionException("Connection fault");
        }
    }
}
