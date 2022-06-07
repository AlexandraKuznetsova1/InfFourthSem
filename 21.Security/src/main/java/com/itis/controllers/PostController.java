package com.itis.controllers;

import com.itis.dtos.AddPostDto;
import com.itis.dtos.PostDto;
import com.itis.dtos.PostsPage;
import com.itis.services.PostsService;
import com.itis.validation.models.ApiError;
import com.itis.validation.models.ValidationExceptionResponse;
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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostsService postsService;

    @Operation(summary = "Получение поста по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Не нашли",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto> getPost(
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postsService.getPost(postId));
    }

    @Operation(summary = "Получение постов по автору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами, по авторам",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли автора",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @GetMapping("/by/{author-id}")
    public ResponseEntity<PostsPage> getAuthorsPosts(
            @Parameter(description = "ID автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Номер страницы") @RequestParam("page") Integer page) {
        return ResponseEntity.ok(postsService.getPosts(authorId, page));
    }

    @Operation(summary = "Создали пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Готовый пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Готовый пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Если автор не найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Parameter(description = "Формочка для создания поста") @Valid @RequestBody AddPostDto addPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPost(addPostDto));
    }

    @Operation(summary = "Обновили пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост с уже обновленной информацией",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Готовый пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Если пост не авторский",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @PostMapping("/update/{author-id}/{post-id}")
    public ResponseEntity<PostDto> updatePost(
            @Parameter(description = "ID автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId,
            @Parameter(description = "Пост с обновленными данными, id не влияет") @Valid PostDto newData) {
        return ResponseEntity.ok(postsService.updatePost(authorId, postId, newData));
    }

    @Operation(summary = "Удаление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Если удаление успешно"

            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Если пост не принадлежит автору",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ApiError.class)
                            )
                    }
            )
    })
    @PostMapping("/delete/{author-id}/{post-id}")
    public ResponseEntity<?> deletePost(
            @Parameter(description = "ID автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId) {
        postsService.deletePost(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }


}

