package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    private final Socket client;
    private static final ConcurrentHashMap<String, String> RESPONSES = new ConcurrentHashMap<>();

    static {
        RESPONSES.put("личности", "Не переходи на личности там, где их нет");
        RESPONSES.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        RESPONSES.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма"
        );
        RESPONSES.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (client;
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
        ) {
            while (!client.isClosed()) {
                String request = in.readLine();
                out.write(RESPONSES.getOrDefault(request, "No answer"));
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
