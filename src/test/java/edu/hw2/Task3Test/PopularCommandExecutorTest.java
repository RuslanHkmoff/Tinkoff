package edu.hw2.Task3Test;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Test when stable connection returns")
    public void test1() {
        ConnectionManager manager = new DefaultConnectionManager(0.0);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, 3);
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Test when fault connection returns")
    public void test2() {
        ConnectionManager manager = new DefaultConnectionManager(1.0);
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, 3);
        String expected = "Connection fault";
        ConnectionException thrown = assertThrows(
            ConnectionException.class,
            executor::updatePackages,
            "Expected connection exception, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expected));
    }
}

