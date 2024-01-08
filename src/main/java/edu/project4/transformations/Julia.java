package edu.project4.transformations;

import edu.project4.model.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Julia implements Transformation {
    @Override
    public Point apply(Point point) {
        double oldX = point.x();
        double oldY = point.y();
        double r = Math.sqrt(Math.sqrt(oldX * oldX + oldY * oldY));
        double theta = Math.atan(oldY / oldX) / 2;
        if (ThreadLocalRandom.current().nextBoolean()) {
            theta += Math.PI;
        }
        return new Point(r * Math.cos(theta), r * Math.sin(theta));
    }
}
