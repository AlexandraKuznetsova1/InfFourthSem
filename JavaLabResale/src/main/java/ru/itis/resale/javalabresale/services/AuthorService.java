package ru.itis.resale.javalabresale.services;

import ru.itis.resale.javalabresale.dto.AuthorDto;
import ru.itis.resale.javalabresale.dto.AuthorsPage;

public interface AuthorService {
    AuthorsPage getAuthors(int page);

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Long authorId, AuthorDto newData);
}
