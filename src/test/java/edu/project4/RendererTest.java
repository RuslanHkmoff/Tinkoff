package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.render.MonoThreadRenderer;
import edu.project4.render.MultithreadingRenderer;
import edu.project4.transformations.Heart;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Tangent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RendererTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("test monothread")
    void test1() {
        MonoThreadRenderer renderer = new MonoThreadRenderer();
        assertDoesNotThrow(() -> renderer.render(
            FractalImage.create(500, 500), new Rect(-4, -3, 8, 6),
            List.of(
                new Heart(),
                new Tangent(),
                new Sinusoidal()
            ), 100, 100, 10, 4
        ));
    }

    @Test
    @DisplayName("test multithread")
    void test2() {
        MultithreadingRenderer renderer = new MultithreadingRenderer(4);
        assertDoesNotThrow(() -> renderer.render(
            FractalImage.create(500, 500), new Rect(-4, -3, 8, 6),
            List.of(
                new Heart(),
                new Tangent(),
                new Sinusoidal()
            ), 100, 100, 10, 4
        ));
    }

    @Test
    @DisplayName("work time")
    void test3() {
        MultithreadingRenderer multithreadingRenderer =
            new MultithreadingRenderer(Runtime.getRuntime().availableProcessors());
        MonoThreadRenderer monoThreadRenderer = new MonoThreadRenderer();

        long start = System.currentTimeMillis();
        multithreadingRenderer.render(
            FractalImage.create(500, 500), new Rect(-4, -3, 8, 6),
            List.of(
                new Heart(),
                new Tangent(),
                new Sinusoidal()
            ), 10000, 1000, 10, 4
        );
        long middle = System.currentTimeMillis();
        monoThreadRenderer.render(
            FractalImage.create(500, 500), new Rect(-4, -3, 8, 6),
            List.of(
                new Heart(),
                new Tangent(),
                new Sinusoidal()
            ), 10000, 1000, 10, 4
        );
        long end = System.currentTimeMillis();
        LOGGER.info(String.format("Multithreading calculate time: %dms", (middle - start)));
        LOGGER.info(String.format("Linear calculate time: %dms", (end - middle)));
    }
}
