package edu.hw2.Task3.managers;

import edu.hw2.Task3.connections.Connection;
import edu.hw2.Task3.connections.ConnectionGenerator;
import edu.hw2.Task3.connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private static final double DEFAULT_PROBABILITY = 0.5;
    private final ConnectionGenerator generator = new ConnectionGenerator(DEFAULT_PROBABILITY);

    @Override
    public Connection getConnection() {
        return new FaultyConnection(generator);
    }
}
