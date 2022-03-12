package com.example.springsecurity.user.model;

import com.example.springsecurity.user.dto.request.RequestUserSignUpDto;
import com.example.springsecurity.user.util.EncryptionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class User {
    private Long id;
    private String account;         // sign-in ID
    private String password;        // sign-in password
    private int role;               // user Role
    private String roleDescription; // user Role Description

    public static User toSignUpModel(RequestUserSignUpDto dto) {
        return new User(dto.getAccount(),
                        EncryptionUtil.encryptPassword(dto.getPassword()),  // password Encryption : Bcrypt
                        dto.getRole());
    }

    public User(String account, String password, int role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

}
