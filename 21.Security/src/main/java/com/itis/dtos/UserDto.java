package com.itis.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель для пользователя")
public class UserDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[A-Za-zА-Яа-я]+")
    @Schema(description = "Имя", example = "Мария")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[A-Za-zА-Яа-я]+")
    @Schema(description = "Фамилия", example = "Иванова")
    private String lastName;
    @NotBlank
    @Email
    @Schema(description = "Почта пользователя", example = "test@gmail.com")
    private String email;
    @NotBlank
    @Size(min = 5)
    @Schema(description = "Логин пользователя, для того чтобы войти в аккаунт", example = "test1")
    private String login;
}
