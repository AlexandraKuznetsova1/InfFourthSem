package ru.itis.resale.javalabresale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotExistsException extends RuntimeException {
    public ProductNotExistsException(Long productId) {
        super("Product with id <" + productId + "> is not exists");
    }
}