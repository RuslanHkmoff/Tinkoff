package edu.project4.transformations;

import edu.project4.model.Point;

public class EyeFish implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.x(), 2));
        return new Point(2 / (r + 1) * point.x(), 2 / (r + 1) * point.y());
    }
}
