package com.example.springsecurity.user.service;

import com.example.springsecurity.user.dto.request.RequestUserSignUpDto;
import com.example.springsecurity.user.exception.ExistAccountException;
import com.example.springsecurity.user.mapper.UserMapper;
import com.example.springsecurity.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("회원가입 완료")
    public void 회원가입서비스_테스트() {
        // given
        String account = "test";
        String password = "1234";
        int role = 1;

        RequestUserSignUpDto dto = new RequestUserSignUpDto(account, password, role);

        // when
        Long id = userService.signUpUser(dto);

        // then
        User expertUserModel = userMapper.findUserById(id).get();
        assertThat(account).isEqualTo(expertUserModel.getAccount());
    }

    @Test
    @DisplayName("아이디 중복")
    public void 아이디중복_테스트() {
        // given  :  ~ 이 주어지고
        RequestUserSignUpDto dto1 = new RequestUserSignUpDto("test", "1", 1);
        RequestUserSignUpDto dto2 = new RequestUserSignUpDto("test", "1", 1);

        // when   :  ~ 이것을 실행했을때
        userService.signUpUser(dto1);

        // service.signUp(requestDto2) 로직이 실행되면 ExistAccountException 예외가 터져야한다.
        ExistAccountException e = assertThrows(ExistAccountException.class, () -> userService.signUpUser(dto2));

        // then   :  ~ 결과가 이것이 나와야 된다.
        assertThat(e.getMessage()).isEqualTo("This Account is exist.");
    }
}