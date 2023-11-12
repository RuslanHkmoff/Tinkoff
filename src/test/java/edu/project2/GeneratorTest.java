package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.RecursiveBacktrackerGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GeneratorTest {
    @Test
    @DisplayName("Test valid grid, height, width")
    void test1() {
        Generator generator = new RecursiveBacktrackerGenerator();
        int height = 10;
        int width = 10;
        Maze maze = generator.generate(height, width);
        assertThat(maze.getHeight()).isEqualTo(height);
        assertThat(maze.getWidth()).isEqualTo(width);
        assertThat(maze.getGrid()).isNotNull();
    }

    @Test
    @DisplayName("Test valid maze")
    void test2() {
        MazeUtilsSpy mazeUtils = new MazeUtilsSpy();
        Maze maze = new RecursiveBacktrackerGenerator(mazeUtils).generate(5, 5);

        int expectedHeight = 5;
        int expectedWidth = 5;
        Cell[][] expectedGrid = new Cell[][] {
            {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL)},
            {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.WALL),
                new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL)},
            {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE), new Cell(2, 2, Cell.Type.WALL),
                new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL)},
            {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE), new Cell(3, 2, Cell.Type.PASSAGE),
                new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.WALL)},
            {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL)}
        };
        assertThat(maze.getHeight()).isEqualTo(expectedHeight);
        assertThat(maze.getWidth()).isEqualTo(expectedWidth);
        assertThat(maze.getGrid()).isEqualTo(expectedGrid);
    }

    static class MazeUtilsSpy extends MazeUtils {

        public MazeUtilsSpy() {
        }

        public Cell[][] createGrid(int height, int width) {
            Cell[][] grid = new Cell[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
            grid[1][1] = new Cell(1, 1, Cell.Type.PASSAGE);
            return grid;
        }

        public boolean isValid(int row, int col, int height, int width) {
            return row >= 0 && row < height
                && col >= 0 && col < width;
        }

        public List<Coordinate> getUnvisitedNeighbours(
            Coordinate curr, Cell[][] grid,
            int[][] shifts, Cell.Type type
        ) {
            int height = grid.length;
            int width = grid[0].length;
            return Arrays.stream(shifts)
                .map(shift -> new Coordinate(curr.row() + shift[0], curr.col() + shift[1]))
                .filter(coord -> this.isValid(coord.row(), coord.col(), height, width))
                .filter(coord -> grid[coord.row()][coord.col()].type() == type)
                .toList();
        }

        public int getRandomIndex(int bound) {
            return 0;
        }
    }
}
