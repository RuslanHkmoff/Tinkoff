package edu.project2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MazeUtils {
    private static final Random RANDOM = new Random();

    public MazeUtils() {
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
        return RANDOM.nextInt(bound);
    }
}
