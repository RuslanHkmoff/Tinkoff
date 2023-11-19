package edu.project3.analyser;

import edu.project3.Log;
import edu.project3.Statistic;
import edu.project3.Utils.AnalyserUtils;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyser implements Analyser<Log> {
    private static final int LIMIT = 10;
    private static final String QUANTITY = "Quantity";

    private static final DateTimeFormatter LOG_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private static final DateTimeFormatter FROM_TO_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Statistic> getStatistic(List<Log> data, String from, String to) {
        List<Log> filteredData = filteredByDate(data, from, to);
        return Stream.of(
            generalStats(filteredData),
            mostFrequentResources(filteredData),
            statusCodes(filteredData)
        ).toList();
    }

    private List<Log> filteredByDate(List<Log> data, String from, String to) {
        List<Log> filtered = data;
        if (from != null) {
            OffsetDateTime fromDate =
                LocalDate.parse(from, FROM_TO_FORMATTER).atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
            filtered = data.stream()
                .filter(log -> {
                    OffsetDateTime logDate = OffsetDateTime.parse(log.time(), LOG_FORMATTER);
                    return logDate.isAfter(fromDate);
                }).toList();
        }
        if (to != null) {
            OffsetDateTime toDate =
                LocalDate.parse(to, FROM_TO_FORMATTER).atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
            filtered = filtered.stream()
                .filter(log -> {
                    OffsetDateTime logDate = OffsetDateTime.parse(log.time(), LOG_FORMATTER);
                    return logDate.isBefore(toDate);
                }).toList();
        }
        return filtered;
    }

    private Statistic generalStats(List<Log> data) {
        String averageSize = getAverageSize(data);
        String earliest = getEarliestRequest(data);
        String latest = getLatestRequest(data);
        return new Statistic(
            "General Stats",
            "Metric",
            "Value",
            mapOfElements(data.size(), averageSize, earliest, latest)
        );
    }

    private Map<String, String> mapOfElements(int size, String averageSize, String earliest, String latest) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Number of requests", Integer.toString(size));
        map.put("Average size", averageSize);
        map.put("Earliest request", earliest);
        map.put("Latest request", latest);
        return map;
    }

    private String getAverageSize(List<Log> data) {
        OptionalDouble average = data.stream()
            .mapToLong(Log::size)
            .average();
        if (average.isEmpty()) {
            return "-";
        }
        return (int) average.getAsDouble() + "b";
    }

    private String getEarliestRequest(List<Log> data) {
        return data.stream()
            .map(Log::time)
            .min(AnalyserUtils::compareDates).orElse("-");
    }

    private String getLatestRequest(List<Log> data) {
        return data.stream()
            .map(Log::time)
            .max(AnalyserUtils::compareDates).orElse("-");
    }

    private Statistic statusCodes(List<Log> data) {
        Map<String, String> codes = data.stream()
            .collect(Collectors.groupingBy(Log::status, Collectors.counting()))
            .entrySet().stream()
            .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
            .limit(LIMIT)
            .collect(Collectors.toMap(
                entry -> entry.getKey().toString(),
                entry -> entry.getValue().toString(),
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
        return new Statistic("Status codes", "Code", QUANTITY, codes);
    }

    private Statistic mostFrequentResources(List<Log> data) {
        Map<String, String> resources = data.stream()
            .collect(Collectors.groupingBy(Log::resource, Collectors.counting()))
            .entrySet().stream()
            .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
            .limit(LIMIT)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().toString(),
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
        return new Statistic("Most frequent resources", "Resource", QUANTITY, resources);
    }
}
