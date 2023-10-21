package edu.hw2.Task3;

import edu.hw2.Task3.excpetions.ConnectionException;

public class FaultyConnection implements Connection {
    private boolean connectionOpen = true;
    private Double faultyProbability = null;

    public FaultyConnection(Double faultyProbability) {
        this.faultyProbability = faultyProbability;
    }

    public FaultyConnection() {
    }

    @Override
    public void execute(String command) {
        if (!connectionOpen) {
            throw new ConnectionException("Unable to connect, connection closed");
        }
        if ((faultyProbability == null && ConnectionUtils.getProbabilityFault())
            || Math.random() < faultyProbability) {
            throw new ConnectionException("Connection fault");
        }
    }

    @Override
    public void close() {
        connectionOpen = false;
    }
}
