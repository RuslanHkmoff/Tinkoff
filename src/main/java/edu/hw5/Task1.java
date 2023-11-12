package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private static final String TIME_REGEX =
        "(^\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$";
    private static final String FORMAT = "yyyy-MM-dd, HH:mm";

    private Task1() {
    }

    public static Duration averageTimeIn(List<String> sessions) {
        return Duration.ofSeconds((long) sessions.stream()
            .mapToLong(Task1::getDurationOfSession)
            .average()
            .orElse(0.0));
    }

    private static long getDurationOfSession(String session) {
        Pattern pattern = Pattern.compile(TIME_REGEX);
        Matcher matcher = pattern.matcher(session);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Session should take form: " + FORMAT + " - " + FORMAT);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        LocalDateTime begin = LocalDateTime.parse(matcher.group(1), formatter);
        LocalDateTime end = LocalDateTime.parse(matcher.group(2), formatter);
        return Duration.between(begin, end).getSeconds();
    }
}
