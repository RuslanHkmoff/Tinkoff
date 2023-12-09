package edu.project4.model;

public record Pixel(int r, int g, int b, int hitCount) {
    public static Pixel create() {
        return new Pixel(0, 0, 0, 0);
    }
}
