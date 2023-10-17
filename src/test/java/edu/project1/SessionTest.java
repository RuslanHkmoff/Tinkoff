package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {
    private Session session;

    @BeforeEach
    void create() {
        session = new Session(new Word("game"), 4);
    }

    @Test
    @DisplayName("Testing when word is invalid")
    void test1() {
        Word invalidWord = new Word("a");
        String expected = "The word must have a length > 2, actual '" + invalidWord + "'";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Session(invalidWord, 4),
            "Expected new Session to throw IllegalArgumentException, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expected));
    }

    @Test
    @DisplayName("Testing guess when it's successful")
    void test2() {
        GuessResult expected = new GuessResult.SuccessfulGuess("Hit!");
        assertThat(session.guess("g")).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing guess when it's failed")
    void test3() {
        GuessResult expected = new GuessResult.FailedGuess("Missed, mistake 1 out of 4");
        assertThat(session.guess("o")).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing guess when it's invalid")
    void test4() {
        GuessResult expected = new GuessResult.InvalidGuess("Invalid input, please try again");
        assertThat(session.guess("abc")).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing guess win")
    void test5() {
        char[] letters = new char[] {'g', 'a', 'm', 'e'};
        GuessResult expected = new GuessResult.Win("You won!");
        GuessResult currResult = null;
        for (char letter : letters) {
            currResult = session.guess(String.valueOf(letter));
        }
        assertThat(currResult).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing guess give up")
    void test6() {
        GuessResult expected = new GuessResult.Lose("You've given up!");
        assertThat(session.guess("give up")).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing guess lose")
    void test7() {
        char[] letters = new char[] {'q', 'w', 'r', 't'};
        GuessResult expected = new GuessResult.Lose("You lose!");
        GuessResult currResult = null;
        for (char letter : letters) {
            currResult = session.guess(String.valueOf(letter));
        }
        assertThat(currResult).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing isGameOver")
    void test8() {
        char[] letters = new char[] {'q', 'w', 'r', 't'};
        assertThat(session.isGameOver()).isEqualTo(false);
        for (char letter : letters) {
            session.guess(String.valueOf(letter));
        }
        assertThat(session.isGameOver()).isEqualTo(true);
    }
}
