package edu.project1;

import java.util.Arrays;

public class Word {
    private final String word;
    private final char[] hiddenWord;
    private final int length;
    private static final char HIDE_SYMBOL = '*';

    public Word(String word) {
        this.word = word;
        this.length = word.length();
        this.hiddenWord = hideWord(word);
    }

    public String getHiddenWord() {
        return String.valueOf(hiddenWord);
    }

    public boolean checkLetter(char letter) {
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == letter && hiddenWord[i] == HIDE_SYMBOL) {
                hiddenWord[i] = letter;
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return length;
    }

    public boolean guessed() {
        return String.valueOf(hiddenWord).equals(word);
    }

    @Override
    public String toString() {
        return word;
    }

    private char[] hideWord(String word) {
        char[] result = new char[word.length()];
        Arrays.fill(result, HIDE_SYMBOL);
        return result;
    }
}
