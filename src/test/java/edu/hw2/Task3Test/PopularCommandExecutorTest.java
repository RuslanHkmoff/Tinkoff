package edu.hw2.Task3Test;

import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.connections.FaultyConnection;
import edu.hw2.Task3.connections.Generator;
import edu.hw2.Task3.connections.StableConnection;
import edu.hw2.Task3.excpetions.MaxAttemptsExceededException;
import edu.hw2.Task3.managers.ConnectionManager;
import edu.hw2.Task3.managers.DefaultConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PopularCommandExecutorTest {
    private ConnectionManager manager;

    @Mock
    private Generator generator;
    @Mock
    private Generator connectionGenerator;

    private PopularCommandExecutor executor;

    @Test
    @DisplayName("Test, successful execute")
    void test1() {
        Mockito.when(generator.generateConnection()).thenReturn(new StableConnection());
        manager = new DefaultConnectionManager(generator);
        executor = new PopularCommandExecutor(manager, 3);
        executor.updatePackages();
    }

    @Test
    @DisplayName("Test, max attempts exceeded")
    void test2() {
        Mockito.doThrow(new RuntimeException("Unknown exception")).when(connectionGenerator).generateException();
        Mockito.when(generator.generateConnection()).thenReturn(new FaultyConnection(connectionGenerator));
        manager = new DefaultConnectionManager(generator);
        executor = new PopularCommandExecutor(manager, 1);
        String expected = "Unable to execute command, number of attempts exceeded";
        MaxAttemptsExceededException thrown = assertThrows(
            MaxAttemptsExceededException.class,
            () -> executor.updatePackages(),
            "Expected MaxAttemptsExceededException, but didn't"
        );
        assertTrue(expected.contains(thrown.getMessage()));
        assertTrue(thrown.getCause() instanceof RuntimeException);
    }
}



