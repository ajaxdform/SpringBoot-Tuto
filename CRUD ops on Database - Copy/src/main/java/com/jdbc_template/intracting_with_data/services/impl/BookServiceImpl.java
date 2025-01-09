package com.jdbc_template.intracting_with_data.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.jdbc_template.intracting_with_data.domain.entities.BookEntity;
import com.jdbc_template.intracting_with_data.repository.BookRepository;
import com.jdbc_template.intracting_with_data.services.BookServices;

@Service
public class BookServiceImpl implements BookServices {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }
    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository
                                            .findAll()
                                            .spliterator(), 
                                            false)
                                            .collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn); 
    }
    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }
    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);   
        }).orElseThrow(() -> new RuntimeException("Book Doesn't Exist"));
    }
    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
    
}