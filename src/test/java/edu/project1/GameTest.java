package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private static final String EXPECTED_SUCCESS = "Hit!";
    private static final String YOU_WON = "You won!";

    @Test
    @DisplayName("Testing, the player makes no mistakes")
    void test() {
        String word = "game";
        Session session = new Session(new Word(word), 4);
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            GuessResult guessResult = session.guess(String.valueOf(ch));
            if (i == word.length() - 1) {
                assertThat(guessResult.message()).isEqualTo(YOU_WON);
            } else {
                assertThat(guessResult.message()).isEqualTo(EXPECTED_SUCCESS);
            }
        }
    }

    @Test
    @DisplayName("Testing, the player makes mistakes but wins")
    void test2() {
        String correctWord = "game";
        String userWord = "gamme";
        String expectedFailed = String.format("Missed, mistake %d out of %d", 1, 6);
        Session session = new Session(new Word(correctWord), 6);
        for (int i = 0; i < userWord.length(); i++) {
            char ch = userWord.charAt(i);
            GuessResult guessResult = session.guess(String.valueOf(ch));
            if (i == 3) {
                assertThat(guessResult.message()).isEqualTo(expectedFailed);
            } else if (i == userWord.length() - 1) {
                assertThat(guessResult.message()).isEqualTo(YOU_WON);
            } else {
                assertThat(guessResult.message()).isEqualTo(EXPECTED_SUCCESS);
            }
        }
    }

    @Test
    @DisplayName("Testing,the player makes mistakes and loses")
    void test3() {
        String correctWord = "game";
        String userWord = "partq";
        String youLose = "You lose!";
        String expectedFailed;
        int countMistake = 1;
        Session session = new Session(new Word(correctWord), 4);
        for (int i = 0; i < userWord.length(); i++) {
            char ch = userWord.charAt(i);
            GuessResult guessResult = session.guess(String.valueOf(ch));
            if (i == 1) {
                assertThat(guessResult.message()).isEqualTo(EXPECTED_SUCCESS);
            } else if (i == userWord.length() - 1) {
                assertThat(guessResult.message()).isEqualTo(youLose);
            } else {
                expectedFailed = String.format("Missed, mistake %d out of %d", countMistake, 4);
                assertThat(guessResult.message()).isEqualTo(expectedFailed);
                countMistake++;
            }
        }
    }

    @Test
    @DisplayName("Testing, when a player gives up")
    void test4() {
        String correctWord = "game";
        char[] userWord = new char[] {'g', 'r', 'a', 'a'};
        String expectedFailed = "Missed, mistake 1 out of 4";
        String giveUp = "You've given up!";
        Session session = new Session(new Word(correctWord), 4);
        GuessResult guessResult;
        for (int i = 0; i < userWord.length; i++) {
            guessResult = session.guess(String.valueOf(userWord[i]));
            if (i == 0 || i == 2) {
                assertThat(guessResult.message()).isEqualTo(EXPECTED_SUCCESS);
            } else if (i == 1) {
                assertThat(guessResult.message()).isEqualTo(expectedFailed);
            } else {
                guessResult = session.guess("give up");
                assertThat(guessResult.message()).isEqualTo(giveUp);
                break;
            }
        }
    }

    @Test
    @DisplayName("Testing, when a player makes an invalid move")
    void test5() {
        String correctWord = "game";
        String expectedInvalidInput = "Invalid input, please try again";
        char[] userWord = new char[] {'g', 'a', 'm', 'e'};
        Session session = new Session(new Word(correctWord), 4);
        GuessResult guessResult;
        guessResult = session.guess("abab");
        assertThat(guessResult.message()).isEqualTo(expectedInvalidInput);
        for (int i = 0; i < userWord.length; i++) {
            guessResult = session.guess(String.valueOf(userWord[i]));
            if (i == userWord.length - 1) {
                assertThat(guessResult.message()).isEqualTo(YOU_WON);
            } else {
                assertThat(guessResult.message()).isEqualTo(EXPECTED_SUCCESS);
            }
        }
    }

    @Test
    @DisplayName("Testing,the word is invalid")
    void test6() {
        String word = "a";
        String expected = "The word must have a length > 2, actual '" + word + "'";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Session(new Word(word), 4),
            "Expected new Session to throw IllegalArgumentException, but didn't"
        );
        assertTrue(thrown.getMessage().contains(expected));
    }
}
