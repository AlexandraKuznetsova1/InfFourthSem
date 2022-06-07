package com.itis.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель для регистрации")
public class SignUpDto {
    @NotBlank
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[A-Za-zА-Яа-я]+")
    @Schema(description = "Имя", example = "Александра")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[A-Za-zА-Яа-я]+")
    @Schema(description = "Фамилия", example = "Кузнецова")
    private String lastName;
    @NotBlank
    @Size(min = 3)
    @Schema(description = "Пароль", example = "password")
    private String password;
    @NotBlank
    @Email
    @Schema(description = "Почта пользователя", example = "test@gmail.com")
    private String email;
    @NotBlank
    @Size(min = 5)
    @Schema(description = "Логин пользователя, для того чтобы войти в аккаунт", example = "test1")
    private String login;

}
