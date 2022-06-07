package ru.itis.resale.javalabresale.services;

import ru.itis.resale.javalabresale.dto.AddProductDto;
import ru.itis.resale.javalabresale.dto.ProductDto;
import ru.itis.resale.javalabresale.models.Product;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(Long productId);

    ProductDto addProduct(AddProductDto productDto);

    List<Product> getAllProducts();

    void deleteProduct(Long authorId, Long productId);

    Product updateProduct(Long authorId, Long productId, ProductDto newData);
}
