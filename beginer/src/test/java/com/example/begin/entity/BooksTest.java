package com.example.begin.entity;

import com.example.begin.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BooksTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void addBookTest() {
    var book = Books.builder().publisher("adc")
                    .title("spring boot")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

    System.out.println(bookRepository.save(book));
    }

}