package edu.project4.processor;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import java.util.Arrays;

public class GammaImageProcessor implements ImageProcessor {

    private static final double GAMMA = 0.4;

    @Override
    public void process(FractalImage image) {
        double max = calculateMax(image);

        int width = image.getWidth();
        int height = image.getHeight();
        Pixel[][] data = new Pixel[width][height];
        for (Pixel[] datum : data) {
            Arrays.fill(datum, new Pixel(0, 0, 0, 0));
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel pixel = image.pixel(x, y);
                double normal = calculateNormal(pixel, max);
                data[x][y] = getCorrectedPixel(pixel, normal);
            }
        }

        image.setData(data);
    }

    private double calculateMax(FractalImage image) {
        double max = 0.0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.hitCount() != 0) {
                    max = Math.max(max, Math.log10(pixel.hitCount()));
                }
            }
        }
        return max;
    }

    private Pixel getCorrectedPixel(Pixel pixel, double normal) {
        int red = (int) (pixel.r() * Math.pow(normal, (1.0 / GAMMA)));
        int green = (int) (pixel.g() * Math.pow(normal, (1.0 / GAMMA)));
        int blue = (int) (pixel.b() * Math.pow(normal, (1.0 / GAMMA)));
        return new Pixel(red, green, blue, pixel.hitCount());
    }

    private double calculateNormal(Pixel pixel, double max) {
        if (pixel.hitCount() == 0) {
            return 0;
        }
        return Math.log10(pixel.hitCount()) / max;
    }
}



