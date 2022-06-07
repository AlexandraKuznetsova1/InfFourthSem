package com.itis.exceptions;

public class PostNotFoundException extends EntityNotFoundException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
