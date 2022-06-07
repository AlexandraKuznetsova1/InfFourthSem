package ru.itis.resale.javalabresale.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.resale.javalabresale.dto.AuthorDto;
import ru.itis.resale.javalabresale.dto.AuthorsPage;
import ru.itis.resale.javalabresale.services.AuthorService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Получение авторов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с авторами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorsPage.class)
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(
            @Parameter(description = "Номер страницы") @RequestParam("page") int page) {
        return ResponseEntity.ok(authorService.getAuthors(page));
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.addAuthor(author));
    }

    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @RequestBody AuthorDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorService.updateAuthor(authorId, newData));
    }
}
