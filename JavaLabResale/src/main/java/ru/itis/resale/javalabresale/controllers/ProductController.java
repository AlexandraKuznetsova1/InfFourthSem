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
import ru.itis.resale.javalabresale.dto.AddProductDto;
import ru.itis.resale.javalabresale.dto.ProductDto;
import ru.itis.resale.javalabresale.models.Product;
import ru.itis.resale.javalabresale.services.ProductService;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Получение продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "продукт найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Продукт не найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductDto> getProduct(
            @Parameter(description = "ID поста") @PathVariable("product-id") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    @Operation(summary = "Все продукты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "нашли все продукты",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class)) }),
            @ApiResponse(responseCode = "400", description = ")))))))",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "не найдено",
                    content = @Content) })
    public ResponseEntity<List<ProductDto>> getPostsPage(){
        return ResponseEntity.ok(ProductDto.from((productService.getAllProducts())));
    }

    @Operation(summary = "Создание продуктв")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "продукт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Автор ты где?",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @PostMapping("/add")
    public ResponseEntity<ProductDto> createProduct(
            @Parameter(description = "Форма для создания продукта") @Valid @RequestBody AddProductDto addProductDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(addProductDto));
    }

    @Operation(summary = "Обновление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " новый продукт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "продукт",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Продукт не найден(",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Не тот автор чел",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @PostMapping("/update/{author-id}/{зкщвгсе-id}")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "id автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "id продукта") @PathVariable("product-id") Long productId,
            @Parameter(description = "newData продукт") @Valid ProductDto newData) {
        return ResponseEntity.ok(productService.updateProduct(authorId, productId, newData));
    }

    @Operation(summary = "Удаление продукта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "уничтожили"

            ),
            @ApiResponse(responseCode = "404", description = "не нашли что уничтожить",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Не тот автор, чел",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ProductDto.class)
                            )
                    }
            )
    })
    @PostMapping("/delete/{author-id}/{product-id}")
    public ResponseEntity<?> deleteProduct(
            @Parameter(description = "id автора") @PathVariable("author-id") Long authorId,
            @Parameter(description = "id продукта") @PathVariable("product-id") Long productId) {
        productService.deleteProduct(authorId, productId);
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }
}
