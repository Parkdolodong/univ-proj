package com.example.begin.repository;

import com.example.begin.entity.Review;
import com.example.begin.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void queryMethodTest() {

//        var user = userRepository.findByNick("reloadUp");
//        user.ifPresent(System.out::println);

        var usersOptional = userRepository.findByCreatedAtAfter(LocalDateTime.now()
                .minusMonths(1L));

//        if(usersOptional.isPresent()) {
//            var users = usersOptional.get();
//            for(var u : users) {
//                System.out.println(u);
//            }
//        }
        usersOptional.ifPresent(users->{
            users.forEach(System.out::println);
        });

    }

    @Rollback(value = false)
    @Transactional
    @Test
    void relationalQueryTest() {
        var user = userRepository.findByNick("reloadUp");
        user.ifPresent(u->{
            var review = Review.builder().title("good title")
                    .contents("good contents")
                    .user(u)
                    .build();

            review = reviewRepository.save(review);
            System.out.println(review);
        });
    }

    @Transactional
    @Test
    void relationalQuerySelectTest() {
        var review  = reviewRepository.findById(4L);
        review.ifPresent(r->{
            System.out.println(r.getUser().getNick());
        });

        var user = userRepository.findByNick("reloadUp");
        user.ifPresent(u->{
            u.getReviews().forEach(System.out::println);
        });
    }
}