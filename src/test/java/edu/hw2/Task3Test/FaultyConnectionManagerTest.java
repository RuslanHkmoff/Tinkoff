package edu.hw2.Task3Test;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaultyConnectionManagerTest {
    @Test
    @DisplayName("Testing faulty connection manager")
    void test() {
        ConnectionManager connectionManager = new FaultyConnectionManager();
        Connection connection = connectionManager.getConnection();
        assertTrue(connection instanceof FaultyConnection);
    }
}
