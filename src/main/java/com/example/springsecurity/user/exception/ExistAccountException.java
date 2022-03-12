package com.example.springsecurity.user.exception;

import com.example.springsecurity.global.error.BusinessException;
import com.example.springsecurity.global.error.ErrorCode;

public class ExistAccountException extends BusinessException {
    public ExistAccountException() {
        super(ErrorCode.EXIST_ACCOUNT);
    }
}
