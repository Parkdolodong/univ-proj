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
@RequestMapping("/engine")
@RequiredArgsConstructor
@Controller
public class ViewsController {

    private final UserService userService;

    @GetMapping(value = "/ex1")
    public void ex1() {
        log.info("ex1-----");
    }

    @GetMapping("/ref")
    public void ref(@RequestParam String uid, Model model) {
        log.info("a tag uid = {}", uid);
        model.addAttribute("result", uid);
    }

    @GetMapping("/ref2/{param1}/{param2}")
    public String ref2(@PathVariable String param1, @PathVariable String param2, Model model) {
        log.info("param1 = {} param2 = {}", param1, param2);

        model.addAttribute("userid", param1);
        model.addAttribute("passwd", param2);

        return "/engine/ref2";

    }

    @GetMapping(value = "/ex2")
    public void ex2(Model model) {
        var users = userService.finalAllUser();
        model.addAttribute("list", users);
    }

    @GetMapping(value = "/redirect")
    public String redirect(RedirectAttributes redirectAttributes) {

        UserDto dto = UserDto.builder().idx(9L)
                .nick("gangstar")
                .userId("korea")
                .userPw("1234")
                .build();

        redirectAttributes.addFlashAttribute("result", "FAIL");
        redirectAttributes.addFlashAttribute("user", dto);
        redirectAttributes.addAttribute("key", "captain");

        return "redirect:/engine/ex3";
    }

    @GetMapping(value = "/ex3")
    public void ex3(@RequestParam String key, Model model){
        log.info("ex3------------");
        model.addAttribute("key", key);
    }

    @GetMapping(value = "/login-form")
    public void loginProc(){

    }

    @PostMapping(value = "/signin")
    public String signIn(UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info("user id = {}, user pw = {}", userDto.getUserId(), userDto.getUserPw());
        /*
        *Todo 자신의 DB에 있는 사용자로 로그인해서 정상적으로 가입된 사용자면 engine/home으로 이동
        * 그렇지 안다면 error 페이지로 이동
        * coolmax/1234
        * */
        var user = userService.findUserInfoByIdAndPw(userDto);

        if(user == null) {
            redirectAttributes.addFlashAttribute("status",
                    HttpStatus.BAD_REQUEST.toString());
            return "redirect:/engine/error";
        }

        redirectAttributes.addFlashAttribute("userinfo",
                user);
        return "redirect:/engine/home";
    }

    @GetMapping(value = "/home")
    public void home(){

    }

    @GetMapping(value = "/ex-layout")
    public void exLayout(){

    }

}
