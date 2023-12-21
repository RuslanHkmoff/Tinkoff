package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String send(String message) {
        String response;
        try (Socket socket = new Socket(host, port)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                out.write(message + "\n");
                out.flush();
                response = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
