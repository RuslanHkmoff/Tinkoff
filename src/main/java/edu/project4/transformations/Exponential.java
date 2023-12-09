package edu.project4.transformations;

import edu.project4.model.Point;

public class Exponential implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y()),
            Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y())
        );
    }
}
