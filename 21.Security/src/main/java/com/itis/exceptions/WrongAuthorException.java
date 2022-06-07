package com.itis.exceptions;

public class WrongAuthorException extends RuntimeException {
    private Object object;
    public WrongAuthorException(String message, Object problematicObject) {
        super(message);
    }

    public WrongAuthorException(String message) {
        super(message);
    }
}
