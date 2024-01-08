package edu.project4.render;

import edu.project4.model.Coefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformations.Transformation;
import edu.project4.utils.RendererUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class MonoThreadRenderer implements Renderer {

    private final Map<Point, Integer> map = new HashMap<>();

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int coeffVariations,
        int symmetry
    ) {
        Pixel[][] data = new Pixel[canvas.getWidth()][canvas.getHeight()];
        for (Pixel[] datum : data) {
            Arrays.fill(datum, Pixel.create());
        }
        Transformation trf = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
        Coefficients[] coefficients = Stream.generate(RendererUtils::getCoefficients)
            .limit(coeffVariations)
            .toArray(Coefficients[]::new);
        for (int num = 0; num < samples; ++num) {
            double newX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
            double newY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
            for (int step = START; step < iterPerSample; ++step) {
                int i = ThreadLocalRandom.current().nextInt(coeffVariations);
                double x = coefficients[i].a() * newX + coefficients[i].b() * newY + coefficients[i].c();
                double y = coefficients[i].d() * newX + coefficients[i].e() * newY + coefficients[i].f();
                Point pw = trf.apply(new Point(x, y));
                if (step >= 0) {
                    double theta = 0;
                    for (int s = 0; s < symmetry; s++) {
                        theta += 2 * Math.PI / symmetry;
                        Point pwr = RendererUtils.rotatePoint(pw, theta);
                        if (!world.contains(pwr)) {
                            continue;
                        }
                        int row = RendererUtils.getXForPixel(pwr, world, canvas);
                        int col = RendererUtils.getYForPixel(pwr, world, canvas);
                        if (!canvas.contains(row, col)) {
                            continue;
                        }
                        Pixel pixel = new Pixel(0, 0, 0, data[row][col].hitCount());
                        Pixel pixelUpdColors = RendererUtils.getPixelUpdateColors(pixel, coefficients[i]);
                        data[row][col] = new Pixel(pixelUpdColors.r(), pixelUpdColors.g(), pixelUpdColors.b(),
                            pixel.hitCount() + 1
                        );
                    }
                }
            }
        }
//        for (int row = 0; row < data.length; row++) {
//            for (int col = 0; col < data[row].length; col++) {
//                Pixel prev = data[row][col];
//                Point point = new Point(row, col);
//                data[row][col] = new Pixel(prev.r(), prev.g(), prev.b(), map.getOrDefault(point, 0));
//            }
//        }
        canvas.setData(data);
        return canvas;
    }
}
