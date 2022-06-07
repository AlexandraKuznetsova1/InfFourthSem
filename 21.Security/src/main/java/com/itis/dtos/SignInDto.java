package com.itis.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NotBlank
    @Size(min = 5)
    @Schema(description = "Логин пользователя, для того чтобы войти в аккаунт", example = "test1")
    private String login;
    @NotBlank
    @Size(min = 3)
    @Schema(description = "Пароль", example = "password")
    private String password;
}
