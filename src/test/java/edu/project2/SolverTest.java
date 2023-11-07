package edu.project2;

import edu.project2.solver.BreadthFirstSearchSolver;
import edu.project2.solver.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolverTest {
    private Maze maze;
    private Solver solver;

    @BeforeEach
    void create() {
        int height = 5;
        int width = 5;
        Cell[][] grid = new Cell[][] {
            {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.PASSAGE), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL)},
            {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE), new Cell(1, 2, Cell.Type.WALL),
                new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL)},
            {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE), new Cell(2, 2, Cell.Type.WALL),
                new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL)},
            {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE), new Cell(3, 2, Cell.Type.PASSAGE),
                new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.WALL)},
            {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.PASSAGE),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL)}
        };
        maze = new Maze(height, width, grid);
        solver = new BreadthFirstSearchSolver();
    }

    @Test
    @DisplayName("Test find path")
    void test1() {
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 1), new Coordinate(4, 2));
        List<Coordinate> expected = List.of(
            new Coordinate(0, 1),
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(3, 1),
            new Coordinate(3, 2),
            new Coordinate(4, 2)
        );
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test path not found")
    void test2() {
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 1), new Coordinate(0, 2));
        List<Coordinate> expected = List.of();
        assertThat(path).isEqualTo(expected);
    }
}
