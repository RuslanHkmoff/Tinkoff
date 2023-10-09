package edu.hw1;

public class Task1 {
    private static final int SECONDS_IN_MINUTES = 60;
    private static final int INVALID_STRING = -1;

    private Task1() {
    }

    public static int minutesToSeconds(String videoLength) {
        if (videoLength == null) {
            return INVALID_STRING;
        }
        String[] parsedLength = videoLength.split(":");
        if (parsedLength.length != 2) {
            return INVALID_STRING;
        }
        int minutes;
        int seconds;
        try {
            minutes = Integer.parseInt(parsedLength[0]);
            seconds = Integer.parseInt(parsedLength[1]);
            if (minutes < 0 || seconds < 0 || seconds >= SECONDS_IN_MINUTES) {
                return INVALID_STRING;
            }
        } catch (NumberFormatException e) {
            return INVALID_STRING;
        }
        return SECONDS_IN_MINUTES * minutes + seconds;
    }
}
