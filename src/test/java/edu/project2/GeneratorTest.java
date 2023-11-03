package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.RecursiveBacktrackerGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GeneratorTest {
    @Test
    @DisplayName("Test generate")
    void test() {
        Generator generator = new RecursiveBacktrackerGenerator();
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
    }
}
