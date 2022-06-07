package com.itis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends EntityNotFoundException{

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
