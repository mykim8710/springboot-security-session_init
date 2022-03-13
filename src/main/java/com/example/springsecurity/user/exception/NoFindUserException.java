package com.example.springsecurity.user.exception;

import com.example.springsecurity.global.error.BusinessException;
import com.example.springsecurity.global.error.ErrorCode;

public class NoFindUserException extends BusinessException {
    public NoFindUserException() {
        super(ErrorCode.NO_FIND_USER);
    }
}
