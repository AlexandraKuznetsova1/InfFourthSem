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
import ru.itis.resale.javalabresale.dto.AddPostDto;
import ru.itis.resale.javalabresale.dto.PostDto;
import ru.itis.resale.javalabresale.models.Post;
import ru.itis.resale.javalabresale.services.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

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
            @ApiResponse(responseCode = "404", description = "Если не нашли пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            )
    })
    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto> getPost(
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping
    @Operation(summary = "Все посты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDto.class)) }),
            @ApiResponse(responseCode = "400", description = ")))))))",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "не найдено",
                    content = @Content) })
    public ResponseEntity<List<PostDto>> getPostsPage(){
        return ResponseEntity.ok(PostDto.from((postService.getAllPosts())));
    }

    @Operation(summary = "Создание поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Созданный пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Созданный пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли автора",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            )
    })
    @PostMapping("/add")
    public ResponseEntity<PostDto> createPost(
            @Parameter(description = "Форма для создания поста") @Valid @RequestBody AddPostDto addPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.addPost(addPostDto));
    }

    @Operation(summary = "Обновление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост с обновленной информацией",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Созданный пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Если пост не принадлежит автору",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            )
    })
    @PostMapping("/update/{author-id}/{post-id}")
    public ResponseEntity<Post> updatePost(
            @Parameter(description = "ID автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId,
            @Parameter(description = "Пост с обновленными данными, id не влияет") @Valid PostDto newData) {
        return ResponseEntity.ok(postService.updatePost(authorId, postId, newData));
    }

    @Operation(summary = "Удаление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Если удаление успешно"

            ),
            @ApiResponse(responseCode = "404", description = "Если не нашли пост",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Если пост не принадлежит автору",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class)
                            )
                    }
            )
    })
    @PostMapping("/delete/{author-id}/{post-id}")
    public ResponseEntity<?> deletePost(
            @Parameter(description = "ID автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "ID поста") @PathVariable("post-id") Long postId) {
        postService.deletePost(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }
}
