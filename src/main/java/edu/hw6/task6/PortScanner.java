package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public class PortScanner {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Map<Integer, String> PORTS = new TreeMap<>(Map.ofEntries(
        Map.entry(53, "DNS"),
        Map.entry(135, "EPMAP"),
        Map.entry(137, "Служба имен NetBIOS"),
        Map.entry(139, "Служба сеансов NetBIOS"),
        Map.entry(1900, "Simple Service Discovery Protocol (SSDP)"),
        Map.entry(5353, "Многоадресный DNS"),
        Map.entry(5355, "Link-Local Multicast Name Resolution (LLMNR)")
    ));

    public List<PortData> scanBusyPorts() {
        List<PortData> result = new ArrayList<>();
        for (Map.Entry<Integer, String> port : PORTS.entrySet()) {
            int portNumber = port.getKey();
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                serverSocket.close();
            } catch (IOException e) {
                result.add(new PortData("TCP", portNumber, PORTS.getOrDefault(portNumber, "")));
            }
            try {
                DatagramSocket datagramSocket = new DatagramSocket(portNumber);
                datagramSocket.close();
            } catch (IOException e) {
                result.add(new PortData("UDP", portNumber, PORTS.getOrDefault(portNumber, "")));
            }
        }
        return result;
    }

    public String getLog() {
        List<PortData> usedPorts = scanBusyPorts();
        StringBuilder log = new StringBuilder("Протокол  Порт  Сервис").append(LINE_SEPARATOR);
        for (PortData port : usedPorts) {
            log.append(port.protocol()).append("       ").append(port.port());
            log.append(" ".repeat(Math.max(0, 6 - Integer.toString(port.port()).length())));
            log.append(port.service()).append(LINE_SEPARATOR);
        }
        return log.toString();
    }
}
