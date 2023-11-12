package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private static final String PASSWORD_REGEX = ".*[~!@#$%^&*|].*";

    private Task4() {
    }

    public static boolean passwordValidation(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        return password != null && pattern.matcher(password).matches();
    }
}
