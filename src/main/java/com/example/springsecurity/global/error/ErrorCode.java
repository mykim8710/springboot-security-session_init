package com.example.springsecurity.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ErrorCode {

    //Sign-up Error
    EXIST_ACCOUNT(400, "S001", "This Account is exist.");


    private int status;
    private String code;
    private String message;
}
