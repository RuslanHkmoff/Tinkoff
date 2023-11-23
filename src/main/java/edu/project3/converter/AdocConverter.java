package edu.project3.converter;

import edu.project3.Statistic;
import edu.project3.Utils.ConverterUtils;
import java.util.List;
import java.util.Map;

public class AdocConverter implements Converter {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String TITLE = "=== ";
    private static final String TABLE_BOUND = "|====";

    @Override
    public String convert(List<Statistic> statistics) {
        StringBuilder adoc = new StringBuilder();
        for (Statistic statistic : statistics) {
            adoc.append(createTable(statistic))
                .append(LINE_SEPARATOR).append(LINE_SEPARATOR);
        }
        return adoc.toString();
    }

    private StringBuilder createTable(Statistic statistic) {
        StringBuilder table = new StringBuilder();
        table.append(TITLE).append(statistic.title()).append(LINE_SEPARATOR);
        table.append(TABLE_BOUND).append(LINE_SEPARATOR);

        String key = statistic.key();
        String value = statistic.value();
        Map<String, String> stats = statistic.stats();
        table.append(putAllValues(key, value, stats));
        table.append(TABLE_BOUND).append(LINE_SEPARATOR);

        return table;
    }

    private StringBuilder putAllValues(String key, String value, Map<String, String> stats) {
        StringBuilder result = new StringBuilder();
        int maxLengthKey = ConverterUtils.getMaxLengthKey(stats, key.length());
        result.append("|").append(ConverterUtils.alignLine(key, maxLengthKey))
            .append(" ").append("|").append(" ")
            .append(value).append(LINE_SEPARATOR);
        for (Map.Entry<String, String> entry : stats.entrySet()) {
            result.append("|").append(ConverterUtils.alignLine(entry.getKey(), maxLengthKey))
                .append(" ").append("|").append(" ")
                .append(entry.getValue()).append(LINE_SEPARATOR);

        }
        return result;
    }
}
