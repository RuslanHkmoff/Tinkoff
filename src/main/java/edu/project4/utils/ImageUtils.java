package edu.project4.utils;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private static final int RED = 16;
    private static final int GREEN = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage fractalImage, Path fileName, ImageFormat format) {
        BufferedImage image =
            new BufferedImage(fractalImage.getWidth(), fractalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < fractalImage.getWidth(); x++) {
            for (int y = 0; y < fractalImage.getHeight(); y++) {
                Pixel pixel = fractalImage.pixel(x, y);
                int rgb = (pixel.r() << RED) | (pixel.g() << GREEN) | pixel.b();
                image.setRGB(x, y, rgb);
            }
        }

        try {
            File output = fileName.toFile();
            ImageIO.write(image, format.name(), output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
