package edu.project3.converter;

import edu.project3.Statistic;
import edu.project3.Utils.ConverterUtils;
import java.util.List;
import java.util.Map;

public class MarkdownConverter implements Converter {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public String convert(List<Statistic> statistics) {
        StringBuilder markdown = new StringBuilder();
        for (Statistic statistic : statistics) {
            markdown.append(createTable(statistic)).append(LINE_SEPARATOR);
        }
        return markdown.toString();
    }

    private StringBuilder createTable(Statistic statistic) {
        StringBuilder table = new StringBuilder();
        table.append("### ").append(statistic.title())
            .append(LINE_SEPARATOR).append(LINE_SEPARATOR);

        String key = statistic.key();
        String value = statistic.value();
        Map<String, String> stats = statistic.stats();
        int maxLengthKey = ConverterUtils.getMaxLengthKey(stats, key.length());
        int maxLengthValue = ConverterUtils.getMaxLengthValue(stats, value.length());

        table.append(putKeyValue(key, value, maxLengthKey, maxLengthValue));

        table.append(createBorder(maxLengthKey, maxLengthValue));

        table.append(putValues(stats, maxLengthKey, maxLengthValue)).append(LINE_SEPARATOR);

        return table;
    }

    private StringBuilder putKeyValue(String key, String value, int maxLengthKey, int maxLengthValue) {
        StringBuilder result = new StringBuilder();
        result.append("| ").append(ConverterUtils.placeInCenter(key, maxLengthKey)).append(" ");
        result.append("| ").append(ConverterUtils.placeInCenter(value, maxLengthValue))
            .append(" ").append("|").append(" ").append(LINE_SEPARATOR);
        return result;
    }

    private StringBuilder putValues(Map<String, String> stats, int maxLengthKey, int maxLengthValue) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : stats.entrySet()) {
            result.append("| ").append(ConverterUtils.placeInCenter(entry.getKey(), maxLengthKey)).append(" ");
            result.append("| ").append(ConverterUtils.placeInCenter(entry.getValue(), maxLengthValue))
                .append(" ").append("|").append(" ").append(LINE_SEPARATOR);
        }
        return result;
    }

    private StringBuilder createBorder(int maxLengthKey, int maxLengthValue) {
        StringBuilder result = new StringBuilder();
        result.append("|:").append("-".repeat(maxLengthKey)).append(":");
        result.append("|").append("-".repeat(maxLengthValue + 1)).append(":|").append(LINE_SEPARATOR);
        return result;
    }
}
