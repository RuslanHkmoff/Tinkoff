package edu.hw4;

public class ValidationError {
    private final String message;
    private final String field;

    public ValidationError(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public ValidationError(String message) {
        this.message = message;
        this.field = null;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageWithField() {
        return field + ": " + message;
    }
}
