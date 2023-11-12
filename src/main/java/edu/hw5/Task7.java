package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private Task7() {
    }

    private static final String REGEX_AT_LEAST_THREE_THIRD_ZERO = "[0-1]{2}0[0-1]*";
    private static final String REGEX_START_AND_END_EQUAL = "(^0$)|(^1$)|(^0[0-1]*0$)|(^1[0-1]*1$)";
    private static final String REGEX_AT_LEAST_1_AND_AT_MOST_3 = "[01]{1,3}";

    public static boolean atLeastThreeThirdZero(String s) {
        Pattern pattern = Pattern.compile(REGEX_AT_LEAST_THREE_THIRD_ZERO);
        return pattern.matcher(s).matches();
    }

    public static boolean startAndEndEqual(String s) {
        Pattern pattern = Pattern.compile(REGEX_START_AND_END_EQUAL);
        return pattern.matcher(s).matches();
    }

    public static boolean atLeast1AtMost3(String s) {
        Pattern pattern = Pattern.compile(REGEX_AT_LEAST_1_AND_AT_MOST_3);
        return pattern.matcher(s).matches();
    }
}
