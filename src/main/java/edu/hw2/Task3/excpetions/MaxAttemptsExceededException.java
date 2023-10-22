package edu.hw2.Task3.excpetions;

public class MaxAttemptsExceededException extends RuntimeException {
    public MaxAttemptsExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
