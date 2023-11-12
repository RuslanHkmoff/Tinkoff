package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private static final String REGEX = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$";

    private Task5() {
    }

    public static boolean numberPlateValidation(String number) {
        Pattern pattern = Pattern.compile(REGEX);
        return number != null && pattern.matcher(number).matches();
    }
}
