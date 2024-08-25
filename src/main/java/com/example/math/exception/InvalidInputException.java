package com.example.math.exception;

public class InvalidInputException extends RuntimeException{
    private String message;
    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }
    public InvalidInputException(String message, Exception ex) {
        super(message, ex);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
