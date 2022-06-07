package ru.itis.resale.javalabresale.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.resale.javalabresale.dto.AuthorDto;
import ru.itis.resale.javalabresale.dto.AuthorsPage;
import ru.itis.resale.javalabresale.exceptions.AuthorNotFoundException;
import ru.itis.resale.javalabresale.models.Author;
import ru.itis.resale.javalabresale.repositories.AuthorsRepository;
import ru.itis.resale.javalabresale.services.AuthorService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.function.Supplier;

import static ru.itis.resale.javalabresale.dto.AuthorDto.from;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorsRepository authorsRepository;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public AuthorsPage getAuthors(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Author> authorPage = authorsRepository.findAll(pageRequest);
        return AuthorsPage.builder()
                .authors(from(authorPage.getContent()))
                .totalPages(authorPage.getTotalPages())
                .build();
    }

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        return from(authorsRepository.save(
                Author.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .build()));
    }

    @Transactional
    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto newData) {
        Author author = authorsRepository.findById(authorId).orElseThrow(
                (Supplier<RuntimeException>)() -> new AuthorNotFoundException("Account not found"));

        author.setFirstName(newData.getFirstName());
        author.setLastName(newData.getLastName());

        return from(authorsRepository.save(author));
    }
}
