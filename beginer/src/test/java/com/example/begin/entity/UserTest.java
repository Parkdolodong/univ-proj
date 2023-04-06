package com.example.begin.entity;

import com.example.begin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void userAddTest() {
        var user = User.builder().userId("coolmax")
                .userPw("1234")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
                .addr("newyork street 13st")
                .nick("developer")
                .build();

        user = userRepository.save(user);
        System.out.println(user);


    }

    @Test
    void findUserTest() {
        var optionalUser = userRepository.findById(3L);

//        if(optionalUser.isPresent()){
//            System.out.println(optionalUser.get());
//        }

        optionalUser.ifPresent(System.out::println);

    }

}