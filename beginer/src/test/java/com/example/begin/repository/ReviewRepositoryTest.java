package com.example.begin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void jpqlTest() {
        var titles  = reviewRepository.findBookTitieByEmail("kavin");
        titles.ifPresent(System.out::println);
    }

}