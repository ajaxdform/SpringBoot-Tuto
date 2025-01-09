package com.jdbc_template.intracting_with_data.services;

import java.util.List;
import java.util.Optional;

import com.jdbc_template.intracting_with_data.domain.entities.BookEntity;

public interface BookServices {
    BookEntity createBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void deleteBook(String isbn);
}