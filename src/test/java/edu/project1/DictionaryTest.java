package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {
    @Test
    @DisplayName("Testing getRandomWord from dictionary")
    void test() {
        List<String> expected = List.of("tinkoff", "game", "hangman", "a");
        Dictionary dictionary = new Dictionary();
        String fromDictionary = dictionary.getRandomWord();
        assertThat(fromDictionary).isEqualTo(contains(expected, fromDictionary));
    }

    private String contains(List<String> list, String wanted) {
        for (String element : list) {
            if (element.equals(wanted)) {
                return element;
            }
        }
        return null;
    }
}
