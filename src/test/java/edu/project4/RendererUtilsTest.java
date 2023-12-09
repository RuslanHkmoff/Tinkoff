package edu.project4;

import edu.project4.model.Coefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.utils.RendererUtils;
import java.awt.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RendererUtilsTest {
    @Test
    @DisplayName("test getCoefficients")
    void test1() {
        Coefficients got = RendererUtils.getCoefficients();
        double a = got.a();
        double b = got.b();
        double d = got.d();
        double e = got.c();
        assertTrue(Math.pow(a, 2) + Math.pow(d, 2) < 1);
        assertTrue(Math.pow(d, 2) + Math.pow(e, 2) < 1);
        assertTrue(Math.pow(a, 2) + Math.pow(d, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 - Math.pow(a * e - b * d, 2));
    }

    @Test
    @DisplayName("test rotatePoint")
    void test2() {
        Point point = new Point(1, 1);
        Point expected = new Point(0.39815702328616975, 1.3570081004945758);
        assertThat(RendererUtils.rotatePoint(point, 0.5)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getPixelUpdateColors")
    void test3() {
        Pixel pixel = new Pixel(0, 0, 0, 0);
        Coefficients coefficients = new Coefficients(1, 1, 1, 1, 1, 1, Color.RED);
        Pixel expected = new Pixel(255, 0, 0, 0);
        assertThat(RendererUtils.getPixelUpdateColors(pixel, coefficients)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getXForPixel")
    void test4() {
        Point point = new Point(1, 1);
        Rect world = new Rect(-4, -3, 8, 6);
        FractalImage canvas = FractalImage.create(10, 10);
        int expected = 6;
        assertThat(RendererUtils.getXForPixel(point, world, canvas)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getYForPixel")
    void test5() {
        Point point = new Point(1, 1);
        Rect world = new Rect(-4, -3, 8, 6);
        FractalImage canvas = FractalImage.create(10, 10);
        int expected = 6;
        assertThat(RendererUtils.getYForPixel(point, world, canvas)).isEqualTo(expected);
    }
}
