package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final int FRIDAY_NUMBER = 13;
    private static final int NUMBER_OF_MONTHS = 12;

    private Task2() {
    }

    public static List<LocalDate> getAllFridays13ths(int year) {
        List<LocalDate> fridays = new ArrayList<>();
        for (int month = 1; month <= NUMBER_OF_MONTHS; month++) {
            LocalDate date = LocalDate.of(year, month, FRIDAY_NUMBER);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(date);
            }
        }
        return fridays;
    }

    public static LocalDate getNextFriday13th(LocalDate date) {
        LocalDate nextFridayThe13th = date;
        do {
            nextFridayThe13th = nextFridayThe13th.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (nextFridayThe13th.getDayOfMonth() != FRIDAY_NUMBER);
        return nextFridayThe13th;
    }
}
