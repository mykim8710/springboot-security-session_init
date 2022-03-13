# spring-security 복습 : 간단한 SIGN IN web
- Session기반 spring security Sign in test
- 권한 별 접근여부 확인
- Thymeleaf Spring Security tag 연습

1) 개발환경
- Intelli J
- Spring Boot 2.6.4
- java 11
- Repository : RDMS(MyBatis)
- db : mysql
- gradle
- view template engine >> thymeleaf(.html)

2) 프로젝트 구조 : 도메인형
```
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── springsecurity
    │   │               ├── SpringsecurityApplication(C)
    │   │               ├── ViewController(C)
    │   │               ├── config    
    │   │               │   ├── mybatis
    │   │               │   │     └── MybatisConfig(C)
    │   │               │   ├── security
    │   │               │   │     ├── handler
    │   │               │   │     │    ├── CustomAccessDeniedHandler(C)
    │   │               │   │     │    ├── CustomAuthenticationFailureHandler(C)
    │   │               │   │     │    └── CustomAuthenticationSuccessHandler(C)        
    │   │               │   │     ├── CustomAuthenticationProvider(C)
    │   │               │   │     ├── CustomUserDetailsService(C)
    │   │               │   │     └── SecurityConfig(C)    
    │   │               ├── global    
    │   │               │   └── error
    │   │               │         ├── BusinessException(C)
    │   │               │         ├── ErrorCode(E)
    │   │               │         ├── ErrorResponse
    │   │               │         └── GlobalExceptionHandler
    │   │               └── user     
    │   │                      ├── controller
    │   │                      │   └── UserApiController(C)    
    │   │                      ├── service
    │   │                      │   └── userrService(C)
    │   │                      ├── mapper
    │   │                      │   └── userMapper(I)
    │   │                      ├── domain   
    │   │                      │   └── User(C)
    │   │                      │── dto
    │   │                      │   ├── request
    │   │                      │   │       └── RequestUserSignUpDto(C)
    │   │                      │   └── response
    │   │                      │           └── ResponseUserSignInDto(C)
    │   │                      └── exception
    │   │                          ├── ExistAccountException(C)
    │   │                          └── NoFindUserException(C)
    │   │        
    │   └── resources
    │       ├── mappers : SQL Query xml
    │       ├── static  : static Resource(js, css, img...)
    │       ├── templates : thymeleaf template : html
    │       ├── log4jdbc.log4j2.properties => SQL Query log 설정
    │       ├── llogback.xml => SQL Query log 설정           
    │       └── application.yaml
```

3) 비즈니스 요구사항 정리
- 데이터: id(pk), account, password, role
- 기능
  - sign-up : 간단한 userInfo insert
  - sign-in : session 기반 spring security
  - todo : user role에 따른 처리
    - role : MASTER(1), USER(2)로 구분(단일 ROLE만 가질수 있다.)
    - role에 따라 url 접근여부 

- 구조
  - controller : Rest API -> UserApiController
  - service : Business Logic -> UserService
  - mapper(dao) :UserMapper(interface)
  - domain Model : User
  - dto : request~User~Dto, response~User~Dto