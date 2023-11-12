package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    public static boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(t);
        return pattern.matcher(s).find();
    }
}
