package edu.project1;

import org.jetbrains.annotations.NotNull;

/**
 * `GuessResult` represents the result of attempting to guess a letter in the Hangman game.
 */
sealed interface GuessResult {
    /**
     * Returns a message describing the result of the guess attempt.
     *
     * @return The message describing the result of the guess attempt.
     */
    @NotNull String message();

    /**
     * The `Lose` record represents the result when the player loses the game.
     */
    record Lose(@NotNull String message) implements GuessResult {
    }

    /**
     * The `Win` record represents the result when the player wins the game.
     */
    record Win(@NotNull String message) implements GuessResult {
    }

    /**
     * The `SuccessfulGuess` record represents the result when the player make a successful guess.
     */
    record SuccessfulGuess(@NotNull String message) implements GuessResult {
    }

    /**
     * The `FailedGuess` record represents the result when the player make a failed guess.
     */
    record FailedGuess(@NotNull String message) implements GuessResult {
    }

    /**
     * The `InvalidGuess` record represents the result when the player make an invalid guess.
     */
    record InvalidGuess(@NotNull String message) implements GuessResult {
    }

}
