package edu.project4.model;

import java.util.Arrays;

public class FractalImage {
    private final int width;

    private final int height;
    private Pixel[][] data;

    public FractalImage(Pixel[][] data, int width, int height) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    private FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Pixel[width][height];
        for (Pixel[] datum : data) {
            Arrays.fill(datum, new Pixel(0, 0, 0, 0));
        }
    }

    public static FractalImage create(int width, int height) {
        return new FractalImage(width, height);
    }

    public boolean contains(int x, int y) {
        return x < width && y < height;
    }

    public Pixel pixel(int x, int y) {
        if (!contains(x, y)) {
            return null;
        }
        return data[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setData(Pixel[][] data) {
        this.data = data;
    }
}
