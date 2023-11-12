package edu.hw5.task3.handler;

import java.time.LocalDate;
import java.util.Optional;

public interface DateParserHandler {
     Optional<LocalDate> handleRequest(String input);
}
