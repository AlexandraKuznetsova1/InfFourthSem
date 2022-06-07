package com.itis.services;

import com.itis.dtos.AuthorDto;
import com.itis.dtos.AuthorsPage;

public interface AuthorsService {

    AuthorDto getAuthor(Long authorId);

    AuthorsPage getAuthorsPage(Integer numberOfPage);

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Long authorId, AuthorDto newData);

}
