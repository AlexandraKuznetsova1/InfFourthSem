package com.itis.controllers;

import com.itis.dtos.AuthorDto;
import com.itis.dtos.AuthorsPage;
import com.itis.services.AuthorsService;
import com.itis.validation.models.ApiError;
import com.itis.validation.models.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/authors")
public class AuthorController {

    private final AuthorsService authorsService;

    @Operation(summary = "Получение авторов")
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
    public ResponseEntity<AuthorsPage> getAuthorsPage(@RequestParam("p") int page) {
        return ResponseEntity.ok(authorsService.getAuthorsPage(page));
    }


    @Operation(summary = "Создание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Созданный автор",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Созданный пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(authorDto));
    }

    @Operation(summary = "Получение автора по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "автор",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Автор не нашли",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @GetMapping("/{author-id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("author-id") Long authorId) {
        return ResponseEntity.ok(authorsService.getAuthor(authorId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "просто автор",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Если нет валидации",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Не нашли автора",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @PostMapping("/update/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @Valid @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorsService.updateAuthor(authorId, authorDto));
    }
}
