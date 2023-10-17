package edu.project1;

import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary {
    private static final List<String> DICTIONARY = List.of("tinkoff", "game", "hangman", "a");
    private static final Random RANDOM = new Random();

    @NotNull public String getRandomWord() {
        return DICTIONARY.get(RANDOM.nextInt(DICTIONARY.size()));
    }
}
