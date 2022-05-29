package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CarEntryDto;
import ru.itis.dto.EntryForm;
import ru.itis.services.ParkingsService;
import ru.itis.validation.http.ValidationErrorDto;
import ru.itis.validation.http.ValidationExceptionResponse;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ParkingsController {

    private final ParkingsService parkingsService;

    @GetMapping("/slots")
    public Integer getSlotsCar(@RequestParam("address") String address) {
        return parkingsService.getSlotsCount(address);
    }

    @PostMapping( "/park")
    public CarEntryDto parkCar(@Valid @RequestBody EntryForm entryForm){
        return parkingsService.parkCar(entryForm);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationExceptionResponse handleException(MethodArgumentNotValidException exception) {
        int i = 0;
        List<ValidationErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });
        return ValidationExceptionResponse.builder()
                .errors(errors)
                .build();
    }
}
