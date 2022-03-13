package com.example.springsecurity.utl;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptTest {

    @Test
    public void bcrypt_테스트() {
        PasswordEncoder pe = new BCryptPasswordEncoder();

        String raw = "1111";
        String encode = pe.encode(raw);

        System.out.println("encode > " +encode);
        System.out.println("raw > " +pe.matches(raw, encode));

    }
}
