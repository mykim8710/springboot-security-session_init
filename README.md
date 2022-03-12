# spring-security 복습 : 간단한 web app
1) 개발환경
- Intelli J
- Spring Boot 2.6.4
- java 11
- Repository : RDMS(MyBatis)
- db : mysql
- gradle
- view template engine >> thymeleaf(.html)

2) 프로젝트 구조



3) 비즈니스 요구사항 정리
- 데이터: id(pk), account, password, role
- 기능
  - sign-up : 간단한 userInfo insert
  - sign-in : spring security
  - todo : user role에 따른 처리
    - role : MASTER(1), USER(2)로 구분(단일 ROLE만 가질수 있다.)

- 구조
  - controller : Rest API -> UserApiController
  - service : Business Logic -> UserService
  - mapper(dao) :UserMapper(interface)
  - domain Model : User, Role
  - dto : request~User~Dto, response~User~Dto