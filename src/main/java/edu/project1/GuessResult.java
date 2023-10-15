package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {

    @NotNull String message();

    record Lose(@NotNull String message) implements GuessResult {
    }

    record Win(@NotNull String message) implements GuessResult {
    }

    record SuccessfulGuess(@NotNull String message) implements GuessResult {
    }

    record FailedGuess(@NotNull String message) implements GuessResult {
    }

    record InvalidGuess(@NotNull String message) implements GuessResult {
    }

}
