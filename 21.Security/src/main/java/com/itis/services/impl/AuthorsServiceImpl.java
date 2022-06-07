package com.itis.services.impl;

import com.itis.dtos.AuthorDto;
import com.itis.dtos.AuthorsPage;
import com.itis.dtos.DtoMapper;
import com.itis.models.Author;
import com.itis.respositories.AuthorsRepository;
import com.itis.services.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final DtoMapper dtoMapper;
    @Value("${blog.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public AuthorDto getAuthor(Long authorId) {
        return dtoMapper.authorToAuthorDto(
                authorsRepository.findById(authorId)
                        .orElseThrow(() -> Author.throwNotFoundExceptionWithId(authorId))
        );
    }

    @Override
    public AuthorsPage getAuthorsPage(Integer numberOfPage) {
        PageRequest pageRequest = PageRequest.of(numberOfPage, defaultPageSize);
        Page<Author> page = authorsRepository.findAll(pageRequest);
        return AuthorsPage.builder()
                .authorDtos(dtoMapper.authorListToAuthorDtoList(page.getContent()))
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        return dtoMapper.authorToAuthorDto(
                authorsRepository.save(dtoMapper.authorDtoToAuthor(author))
        );
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto newData) {
        Author author = authorsRepository.findById(authorId)
                .orElseThrow(() -> Author.throwNotFoundExceptionWithId(authorId));
        author = dtoMapper.updateAuthor(author, newData);
        return dtoMapper.authorToAuthorDto(authorsRepository.save(author));
    }

}
