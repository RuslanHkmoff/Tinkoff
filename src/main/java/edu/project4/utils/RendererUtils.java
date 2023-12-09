package edu.project4.utils;

import edu.project4.model.Coefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class RendererUtils {
    private static final int MAX_COLOR = 256;

    private RendererUtils() {
    }

    public static Coefficients getCoefficients() {
        Coefficients coefficients;
        do {
            coefficients = getRandomCoefficients();
        } while (!isValid(coefficients));
        return coefficients;
    }

    public static Point rotatePoint(Point pw, double theta) {
        return new Point(
            pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta),
            pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta)
        );
    }

    public static Pixel getPixelUpdateColors(Pixel pixel, Coefficients coefficients) {
        int r;
        int g;
        int b;
        if (pixel.hitCount() == 0) {
            r = coefficients.color().getRed();
            g = coefficients.color().getGreen();
            b = coefficients.color().getBlue();

        } else {
            r = pixel.r() + coefficients.color().getRed();
            g = pixel.g() + coefficients.color().getGreen();
            b = pixel.b() + coefficients.color().getBlue();

        }
        return new Pixel(r, g, b, pixel.hitCount());
    }

    public static int getXForPixel(Point pwr, Rect world, FractalImage canvas) {
        return (int) ((pwr.x() - world.x()) * canvas.getWidth() / world.width());
    }

    public static int getYForPixel(Point pwr, Rect world, FractalImage canvas) {
        return (int) ((pwr.y() - world.y()) * canvas.getHeight() / world.height());
    }

    private static boolean isValid(Coefficients coefficients) {
        double a = coefficients.a();
        double b = coefficients.b();
        double d = coefficients.d();
        double e = coefficients.e();
        return Math.pow(a, 2) + Math.pow(d, 2) < 1
            && Math.pow(d, 2) + Math.pow(e, 2) < 1
            && Math.pow(a, 2) + Math.pow(d, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 - Math.pow(a * e - b * d, 2);
    }

    private static Coefficients getRandomCoefficients() {
        return new Coefficients(
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            ThreadLocalRandom.current().nextDouble(-1, 1),
            getRandomColor()
        );
    }

    private static Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(MAX_COLOR),
            ThreadLocalRandom.current().nextInt(MAX_COLOR),
            ThreadLocalRandom.current().nextInt(MAX_COLOR)
        );
    }
}
