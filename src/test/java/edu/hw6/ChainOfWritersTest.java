package edu.hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChainOfWritersTest {
    @Test
    @DisplayName("test writers")
    void test() {
        try (ChainOfWriters writer = new ChainOfWriters(Path.of("src/main/resources/task4/test.txt"))) {
            assertDoesNotThrow(() -> {
                writer.write("Programming is learned by writing programs. ― Brian Kernighan");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/task4/test.txt"))) {
            assertThat(reader.readLine()).isEqualTo(expected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
