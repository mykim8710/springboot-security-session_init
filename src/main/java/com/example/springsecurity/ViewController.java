package com.example.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ViewController {
    @GetMapping("/")
    public String indexView() {
        log.info("[GET / => Root Page View");
        return "index";
    }

    @GetMapping("/sign-up")
    public String signUpView() {
        log.info("[GET /sign-up => sign-up Page View");
        return "user/sign-up";
    }

    @GetMapping("/sign-in")
    public String signInView() {
        log.info("[GET /sign-in => sign-in Page View");
        return "user/sign-in";
    }
}
