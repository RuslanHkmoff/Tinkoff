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
    void setUp(){
        scanner = new PortScanner();
    }

    @Test
    @DisplayName("test scanBusyPorts")
    void test1() {
        List<PortData> expected = List.of(
            new PortData("UDP", 53, "DNS"),
            new PortData("TCP", 135, "EPMAP"),
            new PortData("UDP", 137, "Служба имен NetBIOS"),
            new PortData("TCP", 139, "Служба сеансов NetBIOS"),
            new PortData("UDP", 1900, "Simple Service Discovery Protocol (SSDP)"),
            new PortData("UDP", 5353, "Многоадресный DNS"),
            new PortData("UDP", 5355, "Link-Local Multicast Name Resolution (LLMNR)")
        );
        assertThat(scanner.scanBusyPorts()).isEqualTo(expected);
    }

    @Test
    @DisplayName("test getLog")
    void test2() {
        String expected = """
            Протокол  Порт  Сервис
            UDP       53    DNS
            TCP       135   EPMAP
            UDP       137   Служба имен NetBIOS
            TCP       139   Служба сеансов NetBIOS
            UDP       1900  Simple Service Discovery Protocol (SSDP)
            UDP       5353  Многоадресный DNS
            UDP       5355  Link-Local Multicast Name Resolution (LLMNR)
            """.replace("\n", System.lineSeparator());
        assertThat(scanner.getLog()).isEqualTo(expected);
    }
}
