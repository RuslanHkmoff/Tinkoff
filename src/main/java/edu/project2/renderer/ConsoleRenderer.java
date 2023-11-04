package edu.project2.renderer;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    private static final String WALL = "▓▓▓";
    private static final String PASSAGE = "   ";
    private static final String STEP = " • ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public String render(Maze maze) {
        StringBuilder mazeString = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (Cell[] curr : grid) {
            for (Cell cell : curr) {
                if (cell.type() == Cell.Type.WALL) {
                    mazeString.append(WALL);
                } else {
                    mazeString.append(PASSAGE);
                }
            }
            mazeString.append(LINE_SEPARATOR);
        }
        return mazeString.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazeString = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (Cell[] curr : grid) {
            for (Cell cell : curr) {
                Coordinate currCoordinate = new Coordinate(cell.row(), cell.col());
                if (path.contains(currCoordinate)) {
                    mazeString.append(STEP);
                } else if (cell.type() == Cell.Type.WALL) {
                    mazeString.append(WALL);
                } else {
                    mazeString.append(PASSAGE);
                }
            }
            mazeString.append(LINE_SEPARATOR);
        }

        return mazeString.toString();
    }
}
