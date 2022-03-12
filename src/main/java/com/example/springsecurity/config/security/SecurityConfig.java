package com.example.springsecurity.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // Spring Security를 활성화한다는 의미의 @annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter >> Spring Security의 설정파일로서의 역할을 하기 위해 상속해야 하는 class

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
                .antMatchers("/", "/sign-in", "/sign-up").anonymous()   // View Page
                .antMatchers("/api/user/**").anonymous()                // api

//				.antMatchers("/general").hasAnyAuthority("MASTER", "ADMIN", "GENERAL")
//				.antMatchers("/admin").hasAnyAuthority("ADMIN", "MASTER")
//				.antMatchers("/master").hasAnyAuthority("MASTER")
                .anyRequest().authenticated();

        // 로그인 설정
        http.formLogin()
                .loginPage("/sign-up") 					    // 로그인이 수행될 View api(url)
                .loginProcessingUrl("/api/user/sign-in") 	// 로그인 action url(로그인 form의 action과 일치)
                .usernameParameter("account") 			// 로그인 form의 name(username)과 일치
                .passwordParameter("password") 			// 로그인 form의 name(password)과 일치
		 		.defaultSuccessUrl("/loginSuccess") 	// 로그인 성공 시 이동할 경로.
		 		.failureUrl("/login?error") 			// 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다
//                .successHandler(authenticationSuccessHandler())
//                .failureHandler(authenticationFailureHandler())
                .permitAll();


        // 로그아웃 설정
        http.logout()
                .logoutUrl("/sign-out")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll();

        // Exception 설정
//        http.exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler());
    }

}
