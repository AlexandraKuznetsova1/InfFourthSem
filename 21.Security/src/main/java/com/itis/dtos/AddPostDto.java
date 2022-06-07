package com.itis.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Модель для создания поста с конкретным автором")
public class AddPostDto {
    @Schema(description = "Здесь заголовок", example = "Заголовок")
    @NotBlank
    private String title;
    @Schema(description = "Содержание", example = "Текст")
    @NotBlank
    @Size(min = 3, max = 32)
    private String text;
    @NotNull
    @Min(value = 0)
    @Schema(description = "ID автора, к которому мы добавляем пост", example = "1")
    private Long authorId;
}
