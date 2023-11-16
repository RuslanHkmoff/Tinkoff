package edu.hw6;

import edu.hw6.task6.PortData;
import edu.hw6.task6.PortScanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostScannerTest {
    private PortScanner scanner;

    @BeforeEach
    void setUp() {
        scanner = new PortScanner();
    }

    @Test
    @DisplayName("test scanBusyPorts")
    void test1() {
        List<PortData> expected = List.of(
            new PortData("TCP", 53, "DNS"),
            new PortData("UDP", 53, "DNS"),
            new PortData("TCP", 135, "EPMAP"),
            new PortData("UDP", 135, "EPMAP"),
            new PortData("TCP", 137, "Служба имен NetBIOS"),
            new PortData("UDP", 137, "Служба имен NetBIOS"),
            new PortData("TCP", 139, "Служба сеансов NetBIOS"),
            new PortData("UDP", 139, "Служба сеансов NetBIOS")
        );
        assertThat(scanner.scanBusyPorts()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getLog")
    void test2() {
        String expected = """
            TCP       53    DNS
            UDP       53    DNS
            TCP       135   EPMAP
            UDP       135   EPMAP
            TCP       137   Служба имен NetBIOS
            UDP       137   Служба имен NetBIOS
            TCP       139   Служба сеансов NetBIOS
            UDP       139   Служба сеансов NetBIOS
                        """.replace("\n", System.lineSeparator());
        assertThat(scanner.getLog()).isEqualTo(expected);
    }
}
