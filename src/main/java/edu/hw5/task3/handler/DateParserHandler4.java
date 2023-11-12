package edu.hw5.task3.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateParserHandler4 implements DateParserHandler {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yy");
    private final DateParserHandler next;

    public DateParserHandler4(DateParserHandler next) {
        this.next = next;
    }

    @Override
    public Optional<LocalDate> handleRequest(String date) {
        try {
            return Optional.of(LocalDate.parse(date, FORMATTER));
        } catch (Exception ignored) {
            return next.handleRequest(date);
        }
    }

}
