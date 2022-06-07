package ru.itis.resale.javalabresale.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.resale.javalabresale.models.Author;
import ru.itis.resale.javalabresale.validation.annotation.NotSameNames;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Автор")
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class AuthorDto  {
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Имя", example = "Саша")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 1, max = 10, message = "минимальный размер имени {min}, максимальный - {max}")
    @Schema(description = "Фамилия", example = "Кузнецова")
    private String lastName;

    public static AuthorDto from(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> from(List<Author> authors) {
        return authors.stream().map(AuthorDto::from).collect(Collectors.toList());
    }
}
