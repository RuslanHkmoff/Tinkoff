package edu.hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap();
    }

    @Test
    @DisplayName("test put")
    void test1() {
        assertThat(diskMap.put("1", "a")).isNull();
        assertThat(diskMap.put("1", "b")).isEqualTo("a");
        String expected = "1:b";
        assertThat(readFromFile()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test remove")
    void test2() {
        diskMap.put("1", "a");
        assertThat(diskMap.remove("1")).isEqualTo("a");
        assertThat(readFromFile()).isEqualTo("null");
    }

    @Test
    @DisplayName("test size")
    void test3() {
        diskMap.put("1", "a");
        assertThat(diskMap.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("test clear")
    void test4() {
        diskMap.put("1", "a");
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
        assertThat(readFromFile()).isEqualTo("null");
    }

    @Test
    @DisplayName("test putAll")
    void test5() {
        Map<String, String> test = Map.of("1", "a");
        diskMap.putAll(test);
        String expected = "1:a";
        assertThat(readFromFile()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test invalid file")
    void test6() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new DiskMap("src/main/resources/task1/invalid_disk_map.txt"),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "Element should be match key:value, actual: '3c'";
        assertTrue(expected.contains(thrown.getMessage()));
    }

    @Test
    @DisplayName("test valid file")
    void test7() {
        assertDoesNotThrow(() -> new DiskMap("src/main/resources/task1/valid_disk_map.txt"));
    }

    String readFromFile() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/task1/disk_map.txt"))) {
            data.append(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }
}
