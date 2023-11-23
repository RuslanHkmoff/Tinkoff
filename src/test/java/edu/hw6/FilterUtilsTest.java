package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.FilterUtils.READABLE;
import static edu.hw6.task3.FilterUtils.REGULAR_FILE;
import static edu.hw6.task3.FilterUtils.globMatches;
import static edu.hw6.task3.FilterUtils.largerThan;
import static edu.hw6.task3.FilterUtils.magicNumber;
import static edu.hw6.task3.FilterUtils.regexContains;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterUtilsTest {
    @Test
    @DisplayName("test filters")
    void test() {
        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(largerThan(1_00))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png")).and(regexContains("[test]"));
        String dir = "src/main/resources/task3";
        ArrayList<String> got = new ArrayList<>();
        List<String> expected = List.of(
            "src/main/resources/task3/test_1.png",
            "src/main/resources/task3/test_2.png"
        );
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(dir), filter)) {
            entries.forEach(entry -> got.add(entry.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(got).isEqualTo(expected);
    }
}
