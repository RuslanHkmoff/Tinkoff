package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordTest {
    private Word word;

    @BeforeEach
    void create() {
        word = new Word("game");
    }

    @Test
    @DisplayName("Testing getHiddenWord, without changes")
    void test1() {
        String expectedHidden = "****";
        assertThat(word.getHiddenWord()).isEqualTo(expectedHidden);
    }

    @Test
    @DisplayName("Testing getLength")
    void test2() {
        int expectedLength = 4;
        assertThat(word.getLength()).isEqualTo(expectedLength);
    }

    @Test
    @DisplayName("Testing toString")
    void test3() {
        String expectedToString = "game";
        assertThat(word.toString()).isEqualTo(expectedToString);
    }

    @Test
    @DisplayName("Testing checkLetter, when it exists")
    void test4() {
        String expectedHidden = "g***";
        assertThat(word.checkLetter('g')).isEqualTo(true);
        assertThat(word.getHiddenWord()).isEqualTo(expectedHidden);
    }

    @Test
    @DisplayName("Testing checkLetter, when it doesn't exists")
    void test5() {
        String expectedHidden = "****";
        assertThat(word.checkLetter('o')).isEqualTo(false);
        assertThat(word.getHiddenWord()).isEqualTo(expectedHidden);
    }

    @Test
    @DisplayName("Testing guessed")
    void test6() {
        char[] letters = new char[] {'g', 'a', 'm', 'e'};
        for (char letter : letters) {
            assertThat(word.guessed()).isEqualTo(false);
            word.checkLetter(letter);
        }
        assertThat(word.guessed()).isEqualTo(true);
    }
}
