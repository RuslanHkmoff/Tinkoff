package edu.hw2.Task3.connections;

import edu.hw2.Task3.excpetions.ConnectionException;

public class FaultyConnection implements Connection {
    private boolean connectionClosed = false;
    private final Generator generator;

    public FaultyConnection(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void execute(String command) {
        if (connectionClosed) {
            throw new ConnectionException("Unable to connect, connection closed");
        }
        generator.generateException();
    }

    @Override
    public void close() {
        connectionClosed = true;
    }

}
