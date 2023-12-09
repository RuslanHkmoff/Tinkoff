package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.processor.GammaImageProcessor;
import edu.project4.processor.ImageProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ProcessorTest {
    @Test
    @DisplayName("test GammaProcessor")
    void test() {
        ImageProcessor processor = new GammaImageProcessor();
        FractalImage image = FractalImage.create(10, 10);
        FractalImage copy = FractalImage.create(10, 10);
        assertDoesNotThrow(() -> processor.process(image));
        assertThat(image).isNotEqualTo(copy);
    }
}
