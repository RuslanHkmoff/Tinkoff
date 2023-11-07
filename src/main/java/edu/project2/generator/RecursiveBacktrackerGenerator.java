package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.MazeUtils;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RecursiveBacktrackerGenerator implements Generator {
    private static final int[][] SHIFTS = new int[][] {
        {-2, 0},
        {2, 0},
        {0, -2},
        {0, 2}
    };
    private Deque<Coordinate> stack;
    private final MazeUtils mazeUtils;

    public RecursiveBacktrackerGenerator(MazeUtils mazeUtils) {
        this.mazeUtils = mazeUtils;
    }

    public RecursiveBacktrackerGenerator() {
        this.mazeUtils = new MazeUtils();
    }

    @Override
    public Maze generate(int height, int width) {
        stack = new ArrayDeque<>();
        Cell[][] grid = mazeUtils.createGrid(height, width);
        stack.push(new Coordinate(1, 1));

        while (!stack.isEmpty()) {
            doStep(grid);
        }

        return new Maze(height, width, grid);
    }

    private void doStep(Cell[][] grid) {
        Coordinate curr = stack.peek();
        List<Coordinate> unvisitedNeighbors = getUnvisitedWallNeighbours(curr, grid);
        if (!unvisitedNeighbors.isEmpty()) {
            Coordinate nextCell = removeRandomWall(curr, unvisitedNeighbors, grid);
            stack.push(nextCell);
        } else {
            stack.pop();
        }
    }

    private Coordinate removeRandomWall(Coordinate curr, List<Coordinate> unvisitedNeighbors, Cell[][] grid) {
        int randomIndex = mazeUtils.getRandomIndex(unvisitedNeighbors.size());
        Coordinate next = unvisitedNeighbors.get(randomIndex);
        int row = next.row();
        int col = next.col();

        grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
        int nextPassageRow = (curr.row() + row) / 2;
        int nextPassageCol = (curr.col() + col) / 2;
        grid[nextPassageRow][nextPassageCol] = new Cell(nextPassageRow, nextPassageCol, Cell.Type.PASSAGE);

        return next;
    }

    private List<Coordinate> getUnvisitedWallNeighbours(Coordinate curr, Cell[][] grid) {
        return mazeUtils.getUnvisitedNeighbours(curr, grid, SHIFTS, Cell.Type.WALL);
    }
}
