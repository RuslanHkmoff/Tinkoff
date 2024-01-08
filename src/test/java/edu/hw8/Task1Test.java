package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Task1Test {
    private Server server;

    @Test
    @DisplayName("test close server")
    void test0() throws IOException {
        server = new Server(3435, 2);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> server.close());
    }

    @Test
    @DisplayName("test one client")
    void test1() throws IOException {
        server = new Server(3435, 2);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Client client = new Client("localhost", 3435);
        String expected = "Не переходи на личности там, где их нет";
        assertThat(client.send("личности")).isEqualTo(expected);
        server.close();
    }

    @Test
    @DisplayName("test one client, no answer")
    void test2() throws IOException {
        server = new Server(3435, 2);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Client client = new Client("localhost", 3435);
        String expected = "No answer";
        assertThat(client.send("abacaba")).isEqualTo(expected);
        server.close();
    }

    @Test
    @DisplayName("test multiple client")
    void test3() throws IOException {
        server = new Server(3435, 2);
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread client1 = new Thread(() -> {
            Client client = new Client("localhost", 3435);
            assertThat(client.send("ааа")).isEqualTo("No answer");
        });
        Thread client2 = new Thread(() -> {
            Client client = new Client("localhost", 3435);
            assertThat(client.send("оскорбления")).isEqualTo(
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        });
        Thread client3 = new Thread(() -> {
            Client client = new Client("localhost", 3435);
            assertThat(client.send("интеллект")).isEqualTo("Чем ниже интеллект, тем громче оскорбления");
        });

        client1.start();
        client2.start();
        client3.start();
        try {
            client1.join();
            client2.join();
            client3.join();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.close();
    }
}
