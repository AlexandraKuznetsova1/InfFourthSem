package com.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {
    @Min(value = 0)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 32)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 32)
    private String lastName;
}
