package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class BreadthFirstSearchSolver implements Solver {
    private Cell[][] grid;
    private boolean[][] visited;
    private Queue<Coordinate> queue;
    private Map<Coordinate, Coordinate> parentMap;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        grid = maze.getGrid();
        visited = new boolean[grid.length][grid[0].length];
        List<Coordinate> path = new ArrayList<>();
        queue = new LinkedList<>();
        parentMap = new HashMap<>();
        queue.offer(start);
        parentMap.put(start, null);
        visited[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            doStep(end, path);
        }

        return path;
    }

    private void doStep(Coordinate end, List<Coordinate> path) {
        Coordinate curr = queue.poll();
        Objects.requireNonNull(curr);
        if (curr.equals(end)) {
            reconstructPath(curr, path);
            return;
        }
        checkNeighbours(curr);
    }

    private void checkNeighbours(Coordinate curr) {
        for (int[] dr : DIRECTIONS) {
            int newRow = curr.row() + dr[0];
            int newCol = curr.col() + dr[1];
            if (isValid(newRow, newCol) && !visited[newRow][newCol]
                && grid[newRow][newCol].type() == Cell.Type.PASSAGE) {
                queue.offer(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;
                parentMap.put(new Coordinate(newRow, newCol), curr);
            }
        }
    }

    private void reconstructPath(Coordinate curr, List<Coordinate> path) {
        Coordinate copy = curr;
        while (copy != null) {
            path.add(copy);
            copy = parentMap.get(copy);
        }
        Collections.reverse(path);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < grid.length
            && col >= 0 && col < grid[0].length;
    }
}

