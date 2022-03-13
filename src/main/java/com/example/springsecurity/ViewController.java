package com.example.springsecurity;

import com.example.springsecurity.user.dto.response.ResponseUserSignInDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class ViewController {
    @GetMapping("/")
    public String indexView() {
        log.info("[GET / => Root Page View");
        return "index";
    }

    @GetMapping("/user/sign-up")
    public String signUpView() {
        log.info("[GET /user/sign-up => sign-up Page View");
        return "user/sign-up";
    }

    @GetMapping("/user/sign-in")
    public String signInView() {
        log.info("[GET /user/sign-in => sign-in Page View");
        return "user/sign-in";
    }

    @GetMapping("/home")
    public String homeView(@AuthenticationPrincipal ResponseUserSignInDto dto) {
        log.info("[GET /home => home Page View");
        log.info("ResponseUserSignInDto > " +dto);

        return "home";
    }

    @GetMapping("/user/master")
    public String masterView(@AuthenticationPrincipal ResponseUserSignInDto dto) {
        log.info("[GET /user/master => master Role Page View");
        log.info("ResponseUserSignInDto > " +dto);

        return "user/master";
    }

    @GetMapping("/user/member")
    public String memberView(@AuthenticationPrincipal ResponseUserSignInDto dto) {
        log.info("[GET /user/member => member Role Page View");
        log.info("ResponseUserSignInDto > " +dto);

        return "user/member";
    }
}
