package edu.hw3;

public class Task1 {
    private static final char FIRST_LETTER = 'a';
    private static final char LAST_LETTER = 'z';

    private Task1() {
    }

    public static String atbash(String text) {
        StringBuilder result = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            if (Character.isLetter(symbol)) {
                char currSym = getMirrorSymbol(symbol);
                result.append(currSym);
            } else {
                result.append(symbol);
            }
        }
        return result.toString();
    }

    private static char getMirrorSymbol(char symbol) {
        if (Character.isUpperCase(symbol)) {
            return (char) (Character.toUpperCase(FIRST_LETTER) + (Character.toUpperCase(LAST_LETTER) - symbol));
        }
        return (char) (FIRST_LETTER + (LAST_LETTER - symbol));
    }
}
