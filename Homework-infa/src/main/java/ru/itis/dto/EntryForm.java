package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.annotations.CorrectCarNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CorrectCarNumber(carNumber = "carNumber")
public class EntryForm {
    @NotBlank(message = "The model of must be filled in")
    private String model;

    @NotBlank(message = "The car number of must be filled in")
    private String carNumber;

    private String color;
    private Integer amountOfHours;
    private Long userId;
    @NotBlank(message = "Enter the address of parking")
    private String address;
}
