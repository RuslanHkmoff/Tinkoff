package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private final ExecutorService service;
    private final AtomicBoolean shouldStop = new AtomicBoolean(false);
    private final ServerSocket socket;

    public Server(int port, int threads) throws IOException {
        this.service = Executors.newFixedThreadPool(threads);
        socket = new ServerSocket(port);
    }

    public void start() {
        while (!shouldStop.get() && !socket.isClosed()) {
            try {
                final Socket client = socket.accept();
                service.submit(new ClientHandler(client));
            } catch (SocketException | SocketTimeoutException ignored) {
                // No operations.
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close() {
        shouldStop.set(true);
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.close();
    }
}
