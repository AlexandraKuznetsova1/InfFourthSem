package ru.itis.resale.javalabresale.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.resale.javalabresale.models.Author;
import ru.itis.resale.javalabresale.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByAuthor(Author author, Pageable pageable);
}
