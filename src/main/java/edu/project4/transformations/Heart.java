package edu.project4.transformations;

import edu.project4.model.Point;

public class Heart implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double theta = Math.atan2(point.y(), point.x());
        return new Point(r * Math.sin(theta * r), -r * Math.cos(theta * r));
    }
}
