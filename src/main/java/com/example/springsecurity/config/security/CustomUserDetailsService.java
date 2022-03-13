package com.example.springsecurity.config.security;

import com.example.springsecurity.user.exception.NoFindUserException;
import com.example.springsecurity.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        log.info("account >> " +account);
        return userMapper.findUserByAccount(account).orElseThrow(() -> new UsernameNotFoundException(account));
    }
}
