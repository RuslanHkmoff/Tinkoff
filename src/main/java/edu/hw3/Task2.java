package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final char OPEN_BRACKET = '(';
    private static final String EXCEPTION_MESSAGE = "Invalid brackets sequences";

    private Task2() {
    }

    public static List<String> clusterize(String brackets) {
        validateSequences(brackets);
        int left = 0;
        int balance = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < brackets.length(); i++) {
            char curr = brackets.charAt(i);
            if (curr == OPEN_BRACKET) {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                result.add(brackets.substring(left, i + 1));
                left = i + 1;
            }
        }
        return result;
    }

    private static void validateSequences(String brackets) {
        int balance = 0;
        for (char symbol : brackets.toCharArray()) {
            if (symbol == OPEN_BRACKET) {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
        }
        if (balance != 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
