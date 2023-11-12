package edu.hw5.task3.handler;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParserHandler6 implements DateParserHandler {
    private static final String REGEX = "^(\\d+) days? ago$";
    private final DateParserHandler next;

    public DateParserHandler6(DateParserHandler next) {
        this.next = next;
    }

    @Override
    public Optional<LocalDate> handleRequest(String date) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            int day = Integer.parseInt(matcher.group(1));
            return Optional.of(LocalDate.now().minusDays(day));
        }
        return Optional.empty();
    }
}
