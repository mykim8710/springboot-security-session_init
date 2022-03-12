package com.example.springsecurity.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionUtil {
    public static String encryptPassword(String rawPassword) {
        PasswordEncoder pe =  new BCryptPasswordEncoder();
        return pe.encode(rawPassword);
    }
}
