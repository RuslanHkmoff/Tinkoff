package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DEFAULT_PATH = "src/main/resources/task1/disk_map.txt";
    private final Map<String, String> map;
    private final String path;

    public DiskMap() {
        this.path = DEFAULT_PATH;
        this.map = new HashMap<>();
    }

    public DiskMap(String path) {
        this.path = path;
        this.map = getMapFromFile();

    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String putted = map.put(key, value);
        rewriteFile();
        return putted;
    }

    @Override
    public String remove(Object key) {
        String removed = map.remove(key);
        rewriteFile();
        return removed;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        rewriteFile();
    }

    @Override
    public void clear() {
        map.clear();
        rewriteFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    private Map<String, String> getMapFromFile() {
        Map<String, String> mapFromFile = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] element = line.split(":");
                if (element.length != 2) {
                    throw new IllegalArgumentException("Element should be match key:value, actual: '" + line + "'");
                }
                mapFromFile.put(element[0], element[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapFromFile;
    }

    private void rewriteFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String element = entry.getKey() + ":" + entry.getValue() + LINE_SEPARATOR;
                writer.write(element);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
