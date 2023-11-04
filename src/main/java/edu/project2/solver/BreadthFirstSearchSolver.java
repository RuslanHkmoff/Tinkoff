package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class BreadthFirstSearchSolver implements Solver {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private final Queue<Coordinate> queue;
    private final Map<Coordinate, Coordinate> parentMap;
    private Cell[][] grid;
    private boolean[][] visited;

    public BreadthFirstSearchSolver() {
        queue = new LinkedList<>();
        parentMap = new HashMap<>();
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        grid = maze.getGrid();
        visited = new boolean[grid.length][grid[0].length];
        queue.offer(start);
        parentMap.put(start, null);
        visited[start.row()][start.col()] = true;
        Coordinate curr = start;

        while (!queue.isEmpty()) {
            curr = doStep();
            if (curr.equals(end)) {
                break;
            }
        }

        return reconstructPath(curr, end);
    }

    private Coordinate doStep() {
        Coordinate curr = queue.poll();
        checkNeighbours(curr);
        return curr;
    }

    private void checkNeighbours(Coordinate curr) {
        for (int[] dr : DIRECTIONS) {
            int newRow = curr.row() + dr[0];
            int newCol = curr.col() + dr[1];
            if (MazeUtils.isValid(newRow, newCol, grid.length, grid[0].length) && !visited[newRow][newCol]
                && grid[newRow][newCol].type() == Cell.Type.PASSAGE) {
                queue.offer(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;
                parentMap.put(new Coordinate(newRow, newCol), curr);
            }
        }
    }

    private List<Coordinate> reconstructPath(Coordinate curr, Coordinate end) {
        if (!Objects.equals(curr, end)) {
            return new ArrayList<>();
        }
        List<Coordinate> reconstructedPath = new ArrayList<>();
        Coordinate copy = curr;
        while (copy != null) {
            reconstructedPath.add(copy);
            copy = parentMap.get(copy);
        }
        Collections.reverse(reconstructedPath);
        return reconstructedPath;
    }
}

