package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.MazeUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearchSolver implements Solver {
    private static final int[][] SHIFTS = new int[][] {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    private final Set<Coordinate> visited;
    private final Map<Coordinate, Coordinate> prior;

    public DepthFirstSearchSolver() {
        visited = new HashSet<>();
        prior = new HashMap<>();
    }

    private Cell[][] grid;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        grid = maze.getGrid();
        // visited.add(new Coordinate(0, 1));
        dfs(start, start, end);
        return reconstructPath(start, end);
    }

    private List<Coordinate> reconstructPath(Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate v = end; !start.equals(v); v = prior.get(v)) {
            if (v == null) {
                return new ArrayList<>();
            }
            path.add(v);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private void dfs(Coordinate v, Coordinate from, Coordinate end) {
        if (visited.contains(v) || v.equals(end)) {
            return;
        }
        visited.add(v);
        prior.put(v, from);
        List<Coordinate> neighbours = getUnvisitedPassageNeighbours(v, grid);
        for (Coordinate next : neighbours) {
            dfs(next, v, end);
        }
    }

    private List<Coordinate> getUnvisitedPassageNeighbours(Coordinate curr, Cell[][] grid) {
        return MazeUtils.getUnvisitedNeighbours(curr, grid, SHIFTS, Cell.Type.PASSAGE)
            .stream()
            .filter(coordinate -> !visited.contains(coordinate))
            .toList();
    }
}
