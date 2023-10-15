package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GuessResultTest {

    @Test
    public void testLose() {
        GuessResult lose = new GuessResult.Lose("You lose!");
        String expectedMessage = "You lose!";
        assertThat(lose).isInstanceOf(GuessResult.Lose.class);
        assertThat(lose.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void testWin() {
        GuessResult win = new GuessResult.Win("You won!");
        String expectedMessage = "You won!";
        assertThat(win).isInstanceOf(GuessResult.Win.class);
        assertThat(win.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void testSuccessfulGuess() {
        GuessResult successfulGuess = new GuessResult.SuccessfulGuess("Hit!");
        String expectedMessage = "Hit!";
        assertThat(successfulGuess).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuess.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void testFailedGuess() {
        GuessResult failedGuess = new GuessResult.FailedGuess("Missed, mistake 1 out of 4");
        String expectedMessage = "Missed, mistake 1 out of 4";
        assertThat(failedGuess).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(failedGuess.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void testInvalidGuess() {
        GuessResult invalidGuess = new GuessResult.InvalidGuess("Invalid input, please try again");
        String expectedMessage = "Invalid input, please try again";
        assertThat(invalidGuess).isInstanceOf(GuessResult.InvalidGuess.class);
        assertThat(invalidGuess.message()).isEqualTo(expectedMessage);
    }
}
