package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public String fixString(String str) {
        StringBuilder fixed = new StringBuilder();
        int length = str.length();
        for (int i = 1; i < length; i += 2) {
            fixed.append(str.charAt(i)).append(str.charAt(i - 1));
        }
        if (length % 2 == 1) {
            fixed.append(str.charAt(length - 1));
        }
        return fixed.toString();
    }
}
