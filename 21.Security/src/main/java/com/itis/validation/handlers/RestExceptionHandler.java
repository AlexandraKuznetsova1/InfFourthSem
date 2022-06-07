package com.itis.validation.handlers;

import com.itis.exceptions.EntityNotFoundException;
import com.itis.exceptions.WrongAuthorException;
import com.itis.validation.models.ApiError;
import com.itis.validation.models.ValidationErrorDto;
import com.itis.validation.models.ValidationExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else
                if (error instanceof ObjectError) {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationExceptionResponse.builder()
                .errors(errors)
                .build());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiError handleEntityNotFound(EntityNotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return apiError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongAuthorException.class)
    public ApiError handleWrongAuthor(WrongAuthorException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_MODIFIED);
        apiError.setMessage(ex.getMessage());
        return apiError;
    }
}
