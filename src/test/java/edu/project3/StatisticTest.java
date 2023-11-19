package edu.project3;

import edu.project3.analyser.Analyser;
import edu.project3.analyser.LogAnalyser;
import edu.project3.parser.LogParser;
import edu.project3.parser.Parser;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticTest {
    private Analyser<Log> analyser;
    private List<Log> logs;

    @BeforeEach
    void setUp() {
        Parser<Log> parser = new LogParser(Path.of("src/main/resources/project3/test_statistic.txt"));
        analyser = new LogAnalyser();
        logs = parser.parse();

    }

    @Test
    @DisplayName("test without filter")
    void test1() {
        List<Statistic> expected = List.of(
            new Statistic("General Stats", "Metric", "Value",
                Map.of("Earliest request", "17/May/2015:08:05:23 +0000",
                    "Latest request", "04/Jun/2015:07:06:35 +0000",
                    "Number of requests", "3",
                    "Average size", "222b"
                )
            ),
            new Statistic("Most frequent resources", "Resource", "Quantity",
                Map.of("/downloads/product_1", "3")
            ),
            new Statistic("Status codes", "Code", "Quantity",
                Map.of("404", "2",
                    "304", "1"
                )
            )
        );
        assertThat(analyser.getStatistic(logs, null, null)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test with from")
    void test2() {
        List<Statistic> expected = List.of(
            new Statistic("General Stats", "Metric", "Value",
                Map.of("Earliest request", "04/Jun/2015:07:06:35 +0000",
                    "Latest request", "04/Jun/2015:07:06:35 +0000",
                    "Number of requests", "1",
                    "Average size", "334b"
                )
            ),
            new Statistic("Most frequent resources", "Resource", "Quantity",
                Map.of("/downloads/product_1", "1")
            ),
            new Statistic("Status codes", "Code", "Quantity",
                Map.of("404", "1")
            )
        );
        assertThat(analyser.getStatistic(logs, "2015-06-03", null)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test with to")
    void test3() {
        List<Statistic> expected = List.of(
            new Statistic("General Stats", "Metric", "Value",
                Map.of("Earliest request", "17/May/2015:08:05:23 +0000",
                    "Latest request", "02/Jun/2015:17:06:15 +0000",
                    "Number of requests", "2",
                    "Average size", "167b"
                )
            ),
            new Statistic("Most frequent resources", "Resource", "Quantity",
                Map.of("/downloads/product_1", "2")
            ),
            new Statistic("Status codes", "Code", "Quantity",
                Map.of("304", "1",
                    "404", "1"
                )
            )
        );
        assertThat(analyser.getStatistic(logs, null, "2015-06-04")).isEqualTo(expected);
    }
}
