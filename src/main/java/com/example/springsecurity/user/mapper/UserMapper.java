package com.example.springsecurity.user.mapper;

import com.example.springsecurity.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public void insertUser(User user);
    public Optional<User> findUserByAccount(String account);
    public Optional<User> findUserById(Long id);
}
