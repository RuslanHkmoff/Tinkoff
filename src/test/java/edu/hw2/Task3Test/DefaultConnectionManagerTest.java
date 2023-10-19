package edu.hw2.Task3Test;

import edu.hw2.Task3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultConnectionManagerTest {

    @Test
    @DisplayName("Test Default connection manager")
    public void testGetStableConnection() {
        ConnectionManager connectionManager = new DefaultConnectionManager();
        Connection connection = connectionManager.getConnection();
        assertTrue(connection instanceof FaultyConnection
            || connection instanceof StableConnection);
    }
}
