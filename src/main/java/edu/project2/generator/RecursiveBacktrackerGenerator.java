package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.*;
import java.util.stream.Collectors;

public class RecursiveBacktrackerGenerator implements Generator {
    private Cell[][] grid;
    Deque<Coordinate> stack;
    private static final Random RANDOM = new Random();

    @Override
    public Maze generate(int height, int width) {
        int newHeight = height + 1;
        int newWidth = width + 1;
        grid = new Cell[newHeight][newWidth];
        fill(grid);

        stack = new ArrayDeque<>();
        createStartAndEnd();

        while (!stack.isEmpty()) {
            doStep();
        }

        return new Maze(newHeight, newWidth, grid);
    }

    private void createStartAndEnd() {
        int startRow = 1;
        int startCol = 1;
        int endRow = grid.length - 1;
        int endCol = RANDOM.nextInt(grid[0].length - 3) + 1;
        stack.push(new Coordinate(startRow, startCol));
        grid[startRow - 1][startCol] = new Cell(0, startCol, Cell.Type.PASSAGE);
        grid[startRow][startCol] = new Cell(startRow, startCol, Cell.Type.PASSAGE);
        grid[endRow][endCol] = new Cell(0, startCol, Cell.Type.PASSAGE);

    }

    private void fill(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
    }

    private void doStep() {
        Coordinate curr = stack.peek();
        List<Coordinate> unvisitedNeighbors = getUnvisitedNeighbours(curr);
        if (!unvisitedNeighbors.isEmpty()) {
            removeRandomWall(curr, unvisitedNeighbors);
        } else {
            stack.pop();
        }
    }

    private void removeRandomWall(Coordinate curr, List<Coordinate> unvisitedNeighbors) {
        int randomIndex = RANDOM.nextInt(unvisitedNeighbors.size());
        Coordinate next = unvisitedNeighbors.get(randomIndex);
        int row = next.row();
        int col = next.col();

        grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
        int nextPassageRow = (curr.row() + row) / 2;
        int nextPassageCol = (curr.col() + col) / 2;
        grid[nextPassageRow][nextPassageCol] = new Cell(nextPassageRow, nextPassageCol, Cell.Type.PASSAGE);
        stack.push(next);
    }

    private List<Coordinate> getUnvisitedNeighbours(Coordinate curr) {
        int row = curr.row();
        int col = curr.col();
        int[][] shifts = new int[][] {
            {-2, 0},
            {2, 0},
            {0, -2},
            {0, 2}
        };
        return Arrays.stream(shifts)
            .map(shift -> new Coordinate(row + shift[0], col + shift[1]))
            .filter(cord -> isValid(cord.row(), cord.col()))
            .filter(cord -> grid[cord.row()][cord.col()].type() == Cell.Type.WALL)
            .collect(Collectors.toList());
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < grid.length
            && col >= 0 && col < grid[0].length;
    }
}
