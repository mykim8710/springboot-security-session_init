package com.example.springsecurity.user.controller;

import com.example.springsecurity.user.dto.request.RequestUserSignUpDto;
import com.example.springsecurity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<Long> signUpUser(@Validated @RequestBody RequestUserSignUpDto dto) {
        log.info("[POST] /api/user => Sign up User");
        log.info("RequestUserSignUpDto => " +dto);

        return new ResponseEntity<>(userService.signUpUser(dto), HttpStatus.OK);
    }
}
