package edu.hw2.Task3.connections;

import edu.hw2.Task3.excpetions.ConnectionException;

public class StableConnection implements Connection {
    private boolean connectionOpen = true;

    @Override
    public void execute(String command) {
        if (!connectionOpen) {
            throw new ConnectionException("Unable to connect, connection closed");
        }
    }

    @Override
    public void close() {
        connectionOpen = false;
    }
}
