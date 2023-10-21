package edu.hw2.Task3.managers;

import edu.hw2.Task3.connections.Connection;
import edu.hw2.Task3.connections.Generator;

public class DefaultConnectionManager implements ConnectionManager {
    private final Generator connectionGenerator;

    public DefaultConnectionManager(Generator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    @Override
    public Connection getConnection() {
        return connectionGenerator.generateConnection();
    }
}
