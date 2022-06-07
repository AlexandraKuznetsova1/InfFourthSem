package ru.itis.resale.javalabresale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.resale.javalabresale.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String createdAt;
    private String title;
    private String description;
    private String price;

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt().toString())
                .description(product.getDescription())
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductDto> from(List<Product> products) {
        return products.stream().map(ProductDto::from).collect(Collectors.toList());
    }
}
