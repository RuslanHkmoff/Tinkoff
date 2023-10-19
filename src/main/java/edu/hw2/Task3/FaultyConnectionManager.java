package edu.hw2.Task3;

public class FaultyConnectionManager implements ConnectionManager {
    private final Double faultyProbability;

    public FaultyConnectionManager(Double faultyProbability) {
        this.faultyProbability = faultyProbability;
    }

    public FaultyConnectionManager() {
        this.faultyProbability = null;
    }

    @Override
    public Connection getConnection() {
        if (faultyProbability == null) {
            return new FaultyConnection();
        }
        return new FaultyConnection(faultyProbability);
    }
}
