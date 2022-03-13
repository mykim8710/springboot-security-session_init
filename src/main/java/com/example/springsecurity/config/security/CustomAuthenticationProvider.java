package com.example.springsecurity.config.security;

import com.example.springsecurity.user.dto.response.ResponseUserSignInDto;
import com.example.springsecurity.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교해주는 interface >> authenticate 메서드
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.info("account > " +account);
        log.info("password > " +password);

        User user = (User) customUserDetailsService.loadUserByUsername(account);

        // 비밀번호가 미일치 throw Exception
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(account);
        }

        ResponseUserSignInDto dto = user.toResponseUserSignInDto();
        return new UsernamePasswordAuthenticationToken(dto, null, dto.getRoleList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;    // false -> true
    }

}
