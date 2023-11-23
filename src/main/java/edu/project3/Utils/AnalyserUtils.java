package edu.project3.Utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AnalyserUtils {
    private static final DateTimeFormatter LOG_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private AnalyserUtils() {
    }

    public static int compareDates(String t1, String t2) {
        OffsetDateTime time1 = OffsetDateTime.parse(t1, LOG_FORMATTER);
        OffsetDateTime time2 = OffsetDateTime.parse(t2, LOG_FORMATTER);
        if (time1.isBefore(time2)) {
            return -1;
        } else if (time2.isBefore(time1)) {
            return 1;
        }
        return 0;
    }
}
