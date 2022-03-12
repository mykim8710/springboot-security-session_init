package com.example.springsecurity.user.service;

import com.example.springsecurity.user.dto.request.RequestUserSignUpDto;
import com.example.springsecurity.user.exception.ExistAccountException;
import com.example.springsecurity.user.mapper.UserMapper;
import com.example.springsecurity.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserMapper userMapper;

    // sign-up
    @Transactional
    public Long signUpUser(RequestUserSignUpDto requestUserSignUpDto) {
        // Check Duplicate Accoount
        checkDuplicateAccount(requestUserSignUpDto);

        // set insert domain model
        User insertUserModel = User.toSignUpModel(requestUserSignUpDto);

        // insert User
        userMapper.insertUser(insertUserModel);

        return insertUserModel.getId();
    }

    private void checkDuplicateAccount(RequestUserSignUpDto dto) {
        userMapper.findUserByAccount(dto.getAccount())
                        .ifPresent(user -> {
                            throw new ExistAccountException();
                        });
    }
}
