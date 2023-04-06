package com.example.begin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/page")
@Slf4j
@Controller
public class PageController {
    //http://localhost:8080/page/main
    @GetMapping(value = "/main")
    public void main(){
        log.info("main in ----- ");
    }
}
