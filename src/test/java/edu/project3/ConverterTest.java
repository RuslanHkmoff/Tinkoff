package edu.project3;

import edu.project3.analyser.Analyser;
import edu.project3.analyser.LogAnalyser;
import edu.project3.converter.AdocConverter;
import edu.project3.converter.Converter;
import edu.project3.converter.MarkdownConverter;
import edu.project3.parser.LogParser;
import edu.project3.parser.Parser;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConverterTest {
    private List<Statistic> statistics;

    @BeforeEach
    void setUp() {
        Parser<Log> parser = new LogParser(Path.of("src/main/resources/project3/test_statistic.txt"));
        Analyser<Log> analyser = new LogAnalyser();
        statistics = analyser.getStatistic(parser.parse(), null, null);

    }

    @Test
    @DisplayName("test to markdown")
    void test1() {
        Converter converter = new MarkdownConverter();
        String expected = """
            ### General Stats

            |       Metric       |           Value            |\s
            |:------------------:|---------------------------:|
            | Number of requests |             3              |\s
            |    Average size    |            222b            |\s
            |  Earliest request  | 17/May/2015:08:05:23 +0000 |\s
            |   Latest request   | 04/Jun/2015:07:06:35 +0000 |\s


            ### Most frequent resources

            |       Resource       | Quantity |\s
            |:--------------------:|---------:|
            | /downloads/product_1 |    3     |\s


            ### Status codes

            | Code | Quantity |\s
            |:----:|---------:|
            | 404  |    2     |\s
            | 304  |    1     |\s


            """.replace("\n", System.lineSeparator());
        assertThat(converter.convert(statistics)).isEqualTo(expected);
    }

    @Test
    @DisplayName("test to adoc")
    void test2() {
        Converter converter = new AdocConverter();
        String expected = """
            === General Stats
            |====
            |Metric              | Value
            |Number of requests  | 3
            |Average size        | 222b
            |Earliest request    | 17/May/2015:08:05:23 +0000
            |Latest request      | 04/Jun/2015:07:06:35 +0000
            |====


            === Most frequent resources
            |====
            |Resource              | Quantity
            |/downloads/product_1  | 3
            |====


            === Status codes
            |====
            |Code  | Quantity
            |404   | 2
            |304   | 1
            |====


            """.replace("\n", System.lineSeparator());
        assertThat(converter.convert(statistics)).isEqualTo(expected);
    }
}
