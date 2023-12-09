package edu.project4.transformations;

import edu.project4.model.Point;

public class Power implements Transformation {
    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = Math.sqrt(oldX * oldX + oldY * oldY);
        double theta = Math.atan(oldY / oldX);
        return new Point(
            Math.pow(r, Math.sin(theta)) * Math.cos(theta),
            Math.pow(r, Math.sin(theta)) * Math.sin(theta)
        );
    }
}
