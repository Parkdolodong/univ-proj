package com.example.begin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverServiceTest {

    @Autowired
    NaverService naverService;

    @Test
    void search() {
        naverService.search("국수");
    }
}