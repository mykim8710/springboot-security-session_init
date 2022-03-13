package com.example.springsecurity.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 로그인한 USER의 정보를 담는 객체
@NoArgsConstructor
@Getter
@ToString
public class ResponseUserSignInDto {
    private Long id;
    private String account;
    private int role;
    private String roleDescription;

    public Collection<? extends GrantedAuthority> getRoleList() {
        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roleList.add(new SimpleGrantedAuthority(roleDescription));

        return roleList;
    }

    @Builder
    public ResponseUserSignInDto(Long id, String account, String roleDescription) {
        this.id = id;
        this.account =account;
        this.roleDescription = roleDescription;
    }

}
