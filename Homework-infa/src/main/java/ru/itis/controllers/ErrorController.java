package ru.itis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.exceptions.ErrorEntity;
import ru.itis.exceptions.ValidationException;
import ru.itis.validation.http.ValidationErrorDto;
import ru.itis.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler()
    public ResponseEntity<ErrorEntity> handle(ValidationException ex) {
        return ResponseEntity.status(ex.getEntity().getStatus()).body(ex.getEntity());
    }

}
