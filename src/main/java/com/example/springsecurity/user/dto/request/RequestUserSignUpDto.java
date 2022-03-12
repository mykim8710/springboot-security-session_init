package com.example.springsecurity.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RequestUserSignUpDto {
    private String account;
    private String password;
    private int role;
}
