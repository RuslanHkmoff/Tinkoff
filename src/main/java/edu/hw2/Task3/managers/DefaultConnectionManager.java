package edu.hw2.Task3;

import edu.hw2.Task3.connections.Connection;
import edu.hw2.Task3.connections.FaultyConnection;
import edu.hw2.Task3.connections.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private final Double faultyProbability;

    public DefaultConnectionManager(Double faultyProbability) {
        this.faultyProbability = faultyProbability;
    }

    public DefaultConnectionManager() {
        this.faultyProbability = null;
    }

    @Override
    public Connection getConnection() {
        if (faultyProbability == null) {
            if (ConnectionUtils.getProbabilityFault()) {
                return new FaultyConnection();
            }
            return new StableConnection();
        }
        if (Math.random() < faultyProbability) {
            return new FaultyConnection(faultyProbability);
        }
        return new StableConnection();
    }
}
