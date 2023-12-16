package edu.hw10.task2.cache;

public interface AbstractCache {
    void save(String key, String value);

    String get(String key);
}
