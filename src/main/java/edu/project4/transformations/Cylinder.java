package edu.project4.transformations;

import edu.project4.model.Point;

public class Cylinder implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), point.y());
    }
}
