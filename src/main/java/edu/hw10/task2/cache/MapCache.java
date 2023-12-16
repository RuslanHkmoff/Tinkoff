package edu.hw10.task2.cache;

import java.util.HashMap;
import java.util.Map;

public class MapCache implements AbstractCache {
    private final Map<String, String> map;

    public MapCache() {
        this.map = new HashMap<>();
    }

    @Override
    public void save(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        return map.getOrDefault(key, null);
    }
}
