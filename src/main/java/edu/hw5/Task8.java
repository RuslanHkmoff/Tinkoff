package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private static final String REGEX_ODD_LENGTH = "^[0-1]([01]{2})*$";
    private static final String REGEX_ZERO_ODD_ONE_EVEN = "(^0([01]{2})*$)|(^1[01]([01]{2})*$)";
    private static final String REGEX_WITHOUT_11_111 = "^(?!11$|111$)[01]+$";
    private static final String REGEX_ZEROS_DIVISIBLE_BY_THREE = "^(1*01*01*01*)*$";

    private Task8() {
    }

    /* 8.1*/
    public static boolean oddLength(String s) {
        Pattern pattern = Pattern.compile(REGEX_ODD_LENGTH);
        return pattern.matcher(s).matches();
    }

    /* 8.2 */
    public static boolean zeroOddLengthOrOneEvenLength(String s) {
        Pattern pattern = Pattern.compile(REGEX_ZERO_ODD_ONE_EVEN);
        return pattern.matcher(s).matches();
    }

    /* 8.3 */
    public static boolean zerosDivisibleByThree(String s) {
        Pattern pattern = Pattern.compile(REGEX_ZEROS_DIVISIBLE_BY_THREE);
        return pattern.matcher(s).matches();
    }

    /* 8.4 */
    public static boolean without11and111(String s) {
        Pattern pattern = Pattern.compile(REGEX_WITHOUT_11_111);
        return pattern.matcher(s).matches();
    }
}
