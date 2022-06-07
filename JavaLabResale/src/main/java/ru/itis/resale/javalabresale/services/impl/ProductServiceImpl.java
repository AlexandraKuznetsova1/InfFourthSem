package ru.itis.resale.javalabresale.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.resale.javalabresale.dto.AddProductDto;
import ru.itis.resale.javalabresale.dto.ProductDto;
import ru.itis.resale.javalabresale.enums.State;
import ru.itis.resale.javalabresale.exceptions.AuthorNotExistsException;
import ru.itis.resale.javalabresale.exceptions.AuthorNotFoundException;
import ru.itis.resale.javalabresale.exceptions.PostNotFoundException;
import ru.itis.resale.javalabresale.exceptions.ProductNotFoundException;
import ru.itis.resale.javalabresale.models.Author;
import ru.itis.resale.javalabresale.models.Post;
import ru.itis.resale.javalabresale.models.Product;
import ru.itis.resale.javalabresale.repositories.AuthorsRepository;
import ru.itis.resale.javalabresale.repositories.ProductRepository;
import ru.itis.resale.javalabresale.services.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import static ru.itis.resale.javalabresale.dto.ProductDto.from;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final AuthorsRepository authorsRepository;
    @Override
    public ProductDto getProduct(Long productId) {
        return from(productRepository.findById(productId).orElseThrow((Supplier<RuntimeException>)()
                -> new ProductNotFoundException("product not found")));
    }

    @Override
    public ProductDto addProduct(AddProductDto productDto) {
        Author author = authorsRepository
                .findById(productDto.getAuthorId())
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AuthorNotExistsException(productDto.getAuthorId()));

        Product newProduct = Product.builder()
                .createdAt(LocalDateTime.now())
                .state(State.PUBLISHED)
                .description(productDto.getDescription())
                .title(productDto.getTitle())
                .author(author)
                .price(productDto.getPrice())
                .build();

        return ProductDto.from(productRepository.save(newProduct));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long authorId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("продукт не найден"));
        if(!product.getAuthor().getId().equals(authorId))
            throw new AuthorNotFoundException("Продукт не принадлежит автору с id = " + authorId);
        product.setState(State.DELETED);
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long authorId, Long productId, ProductDto newData) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("продукт"));
        if(!product.getAuthor().getId().equals(authorId))
            throw new AuthorNotFoundException("Продукт не принадлежит автору с id = " + authorId);
        product.setDescription(newData.getDescription());
        product.setTitle(newData.getTitle());
        product.setPrice(newData.getPrice());

        return productRepository.save(product);
    }
}
