package edu.project3;

import edu.project3.parser.LogParser;
import edu.project3.parser.Parser;
import edu.project3.parser.exception.ParseException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    @DisplayName("test parse from file")
    void test1() {
        Path path = Path.of("src/main/resources/project3/test_parser.txt");
        Parser<Log> parser = new LogParser(path);
        List<Log> logs = parser.parse();
//        logs.forEach(l -> {
//            System.out.println("new Log(" + l.addr() + ", " + l.user() + ", " + l.time() + ", " + l.method() + ", "
//                + l.resource() + ", " + l.protocol() + ", " + l.status() + ", " + l.size() + ", " + l.refer() + ", " +
//                l.userAgent());
//        });
        List<Log> expected = List.of(
            new Log(
                "93.180.71.3", "-",
                "17/May/2015:08:05:23 +0000",
                "GET",
                "/downloads/product_1", "HTTP/1.1",
                304,
                0,
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),
            new Log(
                "80.91.33.133",
                "-",
                "17/May/2015:08:05:24 +0000",
                "GET",
                "/downloads/product_1",
                "HTTP/1.1",
                304,
                0,
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
            ),
            new Log("188.138.60.101", "-", "17/May/2015:08:05:49 +0000", "GET",
                "/downloads/product_2", "HTTP/1.1", 304, 0, "-", "Debian APT-HTTP/1.3 (0.9.7.9)"
            ),
            new Log(
                "80.91.33.133",
                "-",
                "17/May/2015:08:05:14 +0000",
                "GET",
                "/downloads/product_1",
                "HTTP/1.1",
                304,
                0,
                "-",
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
            ),
            new Log("46.4.66.76", "-", "17/May/2015:08:05:45 +0000", "GET",
                "/downloads/product_1", "HTTP/1.1", 404, 318, "-", "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
            )
        );
        assertThat(parser.parse()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test invalid log")
    void test2() {
        Path path = Path.of("src/main/resources/project3/invalid_logs.txt");
        Parser<Log> parser = new LogParser(path);
        ParseException thrown = assertThrows(
            ParseException.class,
            parser::parse,
            "Expected ParseException,, but didn't"
        );
        String expected = "Can't parse log: '" +
            "46.4.66.76 - -  \"GET /downloads/product_1 HTTP/1.1\" 404 318 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"" +
            "'";
        assertThat(thrown.getMessage()).isEqualTo(expected);
    }
}
