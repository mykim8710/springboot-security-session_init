package com.example.springsecurity.user.model;

import com.example.springsecurity.user.dto.response.ResponseUserSignInDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@Getter
@ToString
public class User implements UserDetails {
    private Long id;
    private String account;         // sign-in ID
    private String password;        // sign-in password
    private int role;               // user Role
    private String roleDescription; // user Role Description

    public ResponseUserSignInDto toResponseUserSignInDto() {
        return ResponseUserSignInDto.builder()
                                        .id(id)
                                        .account(account)
                                        .roleDescription(roleDescription)
                                        .build();
    }


    @Builder
    public User(String account, String password, int role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
