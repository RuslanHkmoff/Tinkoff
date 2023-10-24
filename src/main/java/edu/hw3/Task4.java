package edu.hw3;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Task4 {
    private static final int UPPER_BOUND = 3999;
    private static final int LOWER_BOUND = 0;

    private Task4() {
    }

    private static final NavigableMap<Integer, String> ROMAN_NUMBERS = new TreeMap<>(Map.ofEntries(
        Map.entry(1000, "M"),
        Map.entry(900, "CM"),
        Map.entry(500, "D"),
        Map.entry(400, "CD"),
        Map.entry(100, "C"),
        Map.entry(90, "XC"),
        Map.entry(50, "L"),
        Map.entry(40, "XL"),
        Map.entry(10, "X"),
        Map.entry(9, "IX"),
        Map.entry(5, "V"),
        Map.entry(4, "IV"),
        Map.entry(1, "I")
    ));

    public static String convertToRoman(int number) {
        validateNumber(number);
        StringBuilder result = new StringBuilder();
        int copy = number;
        for (Map.Entry<Integer, String> entry : ROMAN_NUMBERS.descendingMap().entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();

            while (copy >= value) {
                result.append(symbol);
                copy -= value;
            }
        }
        return result.toString();
    }

    private static void validateNumber(int number) {
        if (number <= LOWER_BOUND || number > UPPER_BOUND) {
            throw new IllegalArgumentException("Number must be from 1 to 3999, actual: '" + number + "'");
        }
    }
}
