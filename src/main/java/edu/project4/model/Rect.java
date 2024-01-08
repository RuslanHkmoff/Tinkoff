package edu.project4.model;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return p.x() < width && p.y() < height && p.y() >= y && p.x() >= x;
    }
}
