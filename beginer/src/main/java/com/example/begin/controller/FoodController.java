package com.example.begin.controller;

import com.example.begin.dto.UserDto;
import com.example.begin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("/myfood")
@RequiredArgsConstructor
@Controller
public class FoodController {

    private final UserService userService;

    @GetMapping("/food")
    public void main(){
        log.info("food main");
    }

}
