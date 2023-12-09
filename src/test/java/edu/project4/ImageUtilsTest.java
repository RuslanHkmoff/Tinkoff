package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageUtilsTest {
    @Test
    @DisplayName("test save")
    void test1() {
        Path path = Path.of("src/main/resources/project4/test_img.img");
        FractalImage image = FractalImage.create(10, 10);
        ImageUtils.save(image, path, ImageFormat.PNG);
        assertTrue(path.toFile().exists());
    }
}
