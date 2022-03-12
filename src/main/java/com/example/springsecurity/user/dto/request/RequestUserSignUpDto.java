package com.example.springsecurity.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@ToString
public class RequestUserSignUpDto {
    @NotBlank(message = "This is required.")
    @Email(message = "account must be in the form of an email.")
    private String account;

    @NotBlank(message = "This is required.")
    private String password;

    private int role;
}
