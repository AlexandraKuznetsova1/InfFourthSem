package com.itis.respositories;


import com.itis.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);
}
