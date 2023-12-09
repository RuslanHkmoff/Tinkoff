package edu.project4.render;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    double X_MIN = -1.777;
    double Y_MIN = -1;
    double X_MAX = 1.777;
    double Y_MAX = 1;
    int START = -20;

    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int coeffVariations,

        int symmetry
    );
}
