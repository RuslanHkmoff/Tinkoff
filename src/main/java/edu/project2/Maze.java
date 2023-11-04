package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        validateArguments(height, width, grid);
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private void validateArguments(int height, int width, Cell[][] grid) {
        if (height < 0) {
            throw new IllegalArgumentException("height must be a positive number, actual '" + height + "'");
        } else if (width < 0) {
            throw new IllegalArgumentException("width must be a positive number, actual '" + width + "'");
        } else if (grid == null) {
            throw new IllegalArgumentException("grid must be not null");
        }
    }
}
