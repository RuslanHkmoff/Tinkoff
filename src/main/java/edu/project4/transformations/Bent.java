package edu.project4.transformations;

import edu.project4.model.Point;

public class Bent implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        if (x >= 0 && y >= 0) {
            return new Point(x, y);
        } else if (x < 0 && y >= 0) {
            return new Point(2 * x, y);
        } else if (x >= 0 && y < 0) {
            return new Point(x, y / 2);
        }
        return new Point(2 * x, y / 2);
    }
}
