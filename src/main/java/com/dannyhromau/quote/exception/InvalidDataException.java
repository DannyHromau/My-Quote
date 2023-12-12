package com.dannyhromau.quote.exception;

public class InvalidDataException extends IllegalArgumentException {
    public InvalidDataException(String message) {
        super(message);
    }
}