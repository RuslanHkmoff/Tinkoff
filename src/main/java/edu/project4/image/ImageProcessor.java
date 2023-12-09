package edu.project4.image;

import edu.project4.model.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    FractalImage process(FractalImage image);
}
