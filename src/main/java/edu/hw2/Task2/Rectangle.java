package edu.hw2.Task2;

public class Rectangle {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(this.width, height);
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(width, this.height);
    }

    public double area() {
        return width * height;
    }
}
