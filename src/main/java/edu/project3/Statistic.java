package edu.project3;

import java.util.Map;

public record Statistic(String title, String key, String value, Map<String, String> stats) {
}
