package com.itis.respositories;

import com.itis.models.Author;
import com.itis.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByAuthor(Author author, Pageable pageable);
}
