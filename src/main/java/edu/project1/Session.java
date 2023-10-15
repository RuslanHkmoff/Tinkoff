package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {
    private static final String GIVE_UP = "give up";
    private final Word word;

    private final int maxAttempts;
    private int attempts;
    private boolean giveUp = false;

    public Session(Word word, int maxAttempts) {
        if (word.getLength() <= 2) {
            throw new IllegalArgumentException("The word must have a length > 2, actual '" + word + "'");
        }
        this.word = word;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    @NotNull public GuessResult guess(String input) {
        if (input.equals(GIVE_UP)) {
            giveUp = true;
            return new GuessResult.Lose("You've given up!");
        }
        if (input.length() != 1) {
            return new GuessResult.InvalidGuess("Invalid input, please try again");
        }
        char guess = input.charAt(0);
        if (word.checkLetter(guess)) {
            return successfulMove();
        }
        return failedMove();
    }

    private GuessResult failedMove() {
        attempts++;
        if (attempts >= maxAttempts) {
            return new GuessResult.Lose("You lose!");
        }
        return new GuessResult.FailedGuess(String.format("Missed, mistake %d out of %d", attempts, maxAttempts));
    }

    private GuessResult successfulMove() {
        if (word.guessed()) {
            return new GuessResult.Win("You won!");
        }
        return new GuessResult.SuccessfulGuess("Hit!");
    }

    public boolean isGameOver() {
        return attempts >= maxAttempts || word.guessed() || giveUp;
    }
}
