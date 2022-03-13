# spring-security 복습 : 간단한 SIGN IN web
- Session 기반 spring security Sign in test
- 권한 별 접근여부 확인
- Thymeleaf Spring Security tag 연습

```
[Session방식 인증 기본원리]

1) 클라이언트 id, pw -> 서버에 최초 sign in 요청
2) id, pw가 맞는지 확인이 되면 Server의 Session이라는 메모리 영역에 해당 session Id, User Object를 저장
3) 서버가 클라이언트(웹브라우저)에 session id를 리턴, 웹브라우저의 Cookie에 session id를 저장
4) 그뒤 클라이언트가 서버에 새로운 요청이 발생되면 session id가 같이 가고 이 session id와 서버에 session id와 비교하여 인증
```


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