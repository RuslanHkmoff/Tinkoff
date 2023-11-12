package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {
    @Test
    @DisplayName("Test getters")
    void test1() {
        int height = 4;
        int width = 4;
        Cell[][] grid = new Cell[height][width];
        Maze maze = new Maze(height, width, grid);
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
        assertThat(maze.getGrid()).isEqualTo(grid);
    }

    @Test
    @DisplayName("Test invalid height")
    void test2() {
        int height = -4;
        int width = 4;
        Cell[][] grid = new Cell[4][width];
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(height, width, grid),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "height must be a positive number, actual '" + height + "'";
        assertTrue(expected.contains(thrown.getMessage()));
    }

    @Test
    @DisplayName("Test invalid width")
    void test3() {
        int height = 4;
        int width = -4;
        Cell[][] grid = new Cell[4][4];
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(height, width, grid),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "width must be a positive number, actual '" + width + "'";
        assertTrue(expected.contains(thrown.getMessage()));
    }

    @Test
    @DisplayName("Test invalid grid")
    void test4() {
        int height = 4;
        int width = 4;
        Cell[][] grid = null;
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Maze(height, width, grid),
            "Expected IllegalArgumentException, but didn't"
        );
        String expected = "grid must be not null";
        assertTrue(expected.contains(thrown.getMessage()));
    }
}
