package ru.itis.resale.javalabresale.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.resale.javalabresale.models.Author;


public interface AuthorsRepository extends JpaRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);
}
