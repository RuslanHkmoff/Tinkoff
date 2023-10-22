package edu.hw2.Task3Test;

import edu.hw2.Task3.connections.FaultyConnection;
import edu.hw2.Task3.connections.Generator;
import edu.hw2.Task3.connections.StableConnection;
import edu.hw2.Task3.managers.ConnectionManager;
import edu.hw2.Task3.managers.DefaultConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DefaultConnectionManagerTest {
    @Mock
    private Generator generator;

    @Test
    @DisplayName("Test when StableConnection returns")
    void test1() {
        Mockito.when(generator.generateConnection()).thenReturn(new StableConnection());
        ConnectionManager manager = new DefaultConnectionManager(generator);
        assertTrue(manager.getConnection() instanceof StableConnection);
    }

    @Test
    @DisplayName("Test when FaultyConnection returns")
    void test2() {
        Mockito.when(generator.generateConnection()).thenReturn(new FaultyConnection(generator));
        ConnectionManager manager = new DefaultConnectionManager(generator);
        assertTrue(manager.getConnection() instanceof FaultyConnection);
    }
}
