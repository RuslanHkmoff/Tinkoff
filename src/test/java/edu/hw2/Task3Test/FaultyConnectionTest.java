package edu.hw2.Task3Test;

import edu.hw2.Task3.connections.Connection;
import edu.hw2.Task3.connections.FaultyConnection;
import edu.hw2.Task3.connections.Generator;
import edu.hw2.Task3.excpetions.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FaultyConnectionTest {
    @Mock
    private Generator generator;

    @Test
    @DisplayName("Test when exception is thrown")
    public void test1() {
        Mockito.doThrow(new ConnectionException("Connection fault")).when(generator).generateException();
        Connection connection = new FaultyConnection(generator);
        String expected = "Connection fault";
        ConnectionException thrown = assertThrows(
            ConnectionException.class,
            () -> connection.execute("command"),
            "Expected ConnectionException, but didn't"
        );
        assertTrue(expected.contains(thrown.getMessage()));
    }

    @Test
    @DisplayName("Test when no exception is thrown")
    public void test2() {
        Mockito.doNothing().when(generator).generateException();
        Connection connection = new FaultyConnection(generator);
        assertDoesNotThrow(() -> connection.execute("command"));
    }

    @Test
    @DisplayName("Test when connection is closed")
    public void test3() throws Exception {
        Connection connection = new FaultyConnection(generator);
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




