package edu.hw5.task3.handler;

import java.time.LocalDate;
import java.util.Optional;

public class DateParserHandler5 implements DateParserHandler {
    private final DateParserHandler next;

    public DateParserHandler5(DateParserHandler next) {
        this.next = next;
    }

    @Override
    public Optional<LocalDate> handleRequest(String date) {
        try {
            return getOptional(date);
        } catch (Exception ignored) {
            return next.handleRequest(date);
        }
    }

    private Optional<LocalDate> getOptional(String date) throws Exception {
        return switch (date) {
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            default -> throw new Exception();
        };
//        if ("tomorrow".equals(date)) {
//            return Optional.of(LocalDate.now().plusDays(1));
//
//        } else if ("yesterday".equals(date)) {
//            return Optional.of(LocalDate.now().minusDays(1));
//        }
//        throw new Exception();
    }
}
