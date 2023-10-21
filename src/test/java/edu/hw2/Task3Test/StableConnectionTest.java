package edu.hw2.Task3Test;

import edu.hw2.Task3.connections.Connection;
import edu.hw2.Task3.excpetions.ConnectionException;
import edu.hw2.Task3.connections.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StableConnectionTest {
    @Test
    @DisplayName("Testing Stable connection")
    void test1() {
        Connection connection = new StableConnection();
        assertDoesNotThrow(() -> connection.execute("command"));
    }

    @Test
    @DisplayName("Testing Stable connection, when it close")
    void test2() throws Exception {
        Connection connection = new StableConnection();
        String expected = "Unable to connect, connection closed";
        connection.close();
        ConnectionException thrown = assertThrows(
            ConnectionException.class,
            () -> connection.execute("command"),
            "Expected ConnectionException, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expected));
    }
}
