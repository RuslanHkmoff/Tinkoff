package edu.project3.Utils;

import java.util.Comparator;
import java.util.Map;

public class ConverterUtils {
    private ConverterUtils() {
    }

    public static String placeInCenter(String s, int maxLen) {
        StringBuilder result = new StringBuilder();
        int diff = maxLen - s.length();
        if (diff % 2 == 0) {
            result.append(" ".repeat(diff / 2))
                .append(s)
                .append(" ".repeat(diff / 2));
        } else {
            result.append(" ".repeat(diff / 2))
                .append(s)
                .append(" ".repeat(diff / 2 + 1));
        }
        return result.toString();
    }

    public static String alignLine(String s, int maxLen) {
        StringBuilder result = new StringBuilder();
        int diff = maxLen - s.length();
        result.append(s).append(" ".repeat(diff + 1));
        return result.toString();
    }

    public static int getMaxLengthKey(Map<String, String> map, int length) {
        return Math.max(map.entrySet().stream()
            .max(Comparator.comparingInt(entry -> entry.getKey().length()))
            .orElseThrow()
            .getKey().length(), length);
    }

    public static int getMaxLengthValue(Map<String, String> map, int length) {
        return Math.max(map.entrySet().stream()
            .max(Comparator.comparingInt(entry -> entry.getValue().length()))
            .orElseThrow()
            .getValue().length(), length);
    }
}
