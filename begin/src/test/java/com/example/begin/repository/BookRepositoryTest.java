package com.example.begin.repository;

import com.example.begin.entity.Books;
import com.example.begin.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;


    private Books getBooks() {
        var book = Books.builder().title("starbucks")
                .publisher("coffee")
                .build();
        return bookRepository.save(book);
    }

    @Rollback(value = false)
    @Transactional
    @Test
    void reviewTest() {
        var userOptional = userRepository.findByNick("reloadUp");
        userOptional.ifPresent(user -> {
            var review = Review.builder().title("good foods")
                    .contents("so kind!")
                    .user(user)
                    .books(getBooks())
                    .build();
            review = reviewRepository.save(review);
            System.out.println(review);
        });
    }

    @Rollback(value = false)
    @Transactional
    @Test
    void findByReviewTest() {
        var userOpt = userRepository.findByNick("reloadUp");
        userOpt.ifPresent(user -> {
            user.getReviews().forEach(review -> {
                System.out.println(review.getBooks().getTitle());
            });
//            user.getReviews().forEach(System.out::println);
        });
    }

    @Rollback(value = false)
    @Transactional
    @Test
    void reviewCascadeTest() {
        var book = Books.builder().publisher("deagaUniv")
                .title("Spring 정복")
                .build();

        var userOptional = userRepository.findByNick("developer");

        userOptional.ifPresent(user -> {
            var review = Review.builder().title("good foods")
                    .contents("so kind!")
                    .user(user)
                    .books(book)
                    .build();

            review = reviewRepository.save(review);
            System.out.println(review);
        });
    }
}