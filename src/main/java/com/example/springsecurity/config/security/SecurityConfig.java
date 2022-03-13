package com.example.springsecurity.config.security;

import com.example.springsecurity.config.security.handler.CustomAccessDeniedHandler;
import com.example.springsecurity.config.security.handler.CustomAuthenticationFailureHandler;
import com.example.springsecurity.config.security.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // Spring Security를 활성화한다는 의미의 @annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter >> Spring Security의 설정파일로서의 역할을 하기 위해 상속해야 하는 class
    private final CustomUserDetailsService customUserDetailsService;

    // 실제 인증을 진행할 Provider 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // 인증을 무시할 경로들을 설정 >> static resource 보안설정
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    // http 관련 인증 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf => csrf 토큰 disable
        http.csrf()
                .disable();

        // 권한별 url접근 설정
        http.authorizeRequests()
                .antMatchers("/", "/user/sign-in", "/user/sign-up").anonymous() // View Page
                .antMatchers("/api/user/**").anonymous()                        // api
				.antMatchers("/user/master").hasAnyAuthority("MASTER")  // ROLE_ 제외, hasRole => ROLE_~~
				.antMatchers("/user/member").hasAnyAuthority("MASTER", "MEMBER") // ROLE_ 재외
                .anyRequest().authenticated();

        // 로그인 설정
        http.formLogin()
                .loginPage("/") 				        // 로그인이 수행될 View api(url)
                .loginProcessingUrl("/sign-in") 	    // 로그인 action url(로그인 form의 action과 일치) >> sign-in page의 mapping과 달라야함
                .usernameParameter("account") 		    // 로그인 form의 name(username)과 일치
                .passwordParameter("password") 		    // 로그인 form의 name(password)과 일치
                .successHandler(authenticationSuccessHandler()) // 인증 성공 Handler
                .failureHandler(authenticationFailureHandler()) // 인증 실패 Handler
                .permitAll();

        // Exception 설정
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());// 접근권한이 없을때 handler

        // 로그아웃 설정
        http.logout()
                .logoutUrl("/sign-out")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
