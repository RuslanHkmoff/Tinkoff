package edu.hw2.Task3Test;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaultyConnectionTest {

    @Test
    @DisplayName("Test faulty connection, when it throw connection exception")
    void test1() {
        String expectedFaultMessage = "Connection fault";
        Connection connection = new FaultyConnection(1.0);
        ConnectionException thrown = assertThrows(
            ConnectionException.class,
            () -> connection.execute("command"),
            "Expected connection exception, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expectedFaultMessage));
    }
    @Test
    @DisplayName("Test faulty connection, when it close")
    void test2() throws Exception {
        String expectedClosedMessage = "Unable to connect, connection closed";
        Connection connection = new FaultyConnection();
        connection.close();
        ConnectionException thrown = assertThrows(
            ConnectionException.class,
            () -> connection.execute("command"),
            "Expected connection exception, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expectedClosedMessage));
    }
    @Test
    @DisplayName("Test faulty connection, when it doesn't throw")
    void test3() throws Exception {
        Connection connection = new FaultyConnection(0.0);
        assertDoesNotThrow(() -> connection.execute("command"));
    }
}
