package edu.hw10.task2.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCache implements AbstractCache {
    private final String pathToDir;

    public FileCache(String pathToDir) throws IOException {
        this.pathToDir = pathToDir;
        Path path = Path.of(pathToDir);
        if (!Files.isDirectory(path)) {
            Files.createDirectory(path);
        }
    }

    @Override
    public void save(String key, String value) {
        Path path = Path.of(pathToDir, key);
        if (Files.exists(path)) {
            return;
        }
        try {
            Files.createFile(path);
            Files.writeString(path, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        Path path = Path.of(pathToDir, key);
        if (!Files.exists(path)) {
            return null;
        }
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
