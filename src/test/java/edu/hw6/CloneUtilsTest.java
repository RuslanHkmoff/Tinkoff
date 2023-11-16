package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CloneUtilsTest {
    @Test
    @DisplayName("test clone")
    void test1() {
        File file = new File("src/main/resources/task2/test.txt");
        CloneUtils.cloneFile(file.toPath());
        assertTrue(Files.exists(Path.of("src/main/resources/task2/test — копия (1).txt")));
        String[] expected = new String[] {"Hello,", "world!"};
        int i = 0;
        try (BufferedReader reader = new BufferedReader
            (new FileReader("src/main/resources/task2/test — копия (1).txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assertThat(line).isEqualTo(expected[i++]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("test invalid path")
    void test2() {
        File file = new File("src/main/resources/task2/testtt.txt");
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> CloneUtils.cloneFile(file.toPath()),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "path doesn't exists 'src/main/resources/task2/testtt.txt'";
        assertTrue(expected.contains(thrown.getMessage()));

    }
}
