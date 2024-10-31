# Assignment_Todo_developed

_Commit 옆 링크 클릭 시 해당 Commit 내역으로 이동_
_JWT Token 및 Spring Security 부분은 완벽하게 숙지하지 못해서 코드를 따라 친 경우가 많음.
공부하면서 하기엔 시간이 너무 부족했기때문에 순서대로 먼저 따라쳐보고 다시 공부해야함._

[가독성용 목차]

-[2024.10.28.](#20241028): Member RUD

-[2024.10.29.](#20241029): Member 'C', Schedule CRUD

-[2024.10.30.](#20241030): Comment CRUD, JWT Token, Spring Security

-[2024.10.31.](#20241031): Code Refactoring, JWT Token, Spring Security, Validation(Exception)

[@Slf4j로 에러 해결](#20241029) 3번 마지막  

### 2024.10.28.

1. 튜터님 피드백 반영 (Commit [1e39096](https://github.com/dameun0527/Assignment_Todo_developed/commit/1e39096719685371acb201aa5cffc7ffa59dde28))
- @DeleteMapping("/{commentId}"), line 44: ok().build() -> noContent().build()
- @PutMapping("/{commentId}"): @PathVariable Long commentId 하나로 줄임
- 변수명 정리 및 코드 획일화
- 일시적 sql 쿼리 주석화


2. Jwt Token 관련 기능 구현을 위한 클래스 파일 추가 (Commit [914233c](https://github.com/dameun0527/Assignment_Todo_developed/commit/914233c2b9f19107041c86b49e37ea13e14362d6))
- JwtAuthFilter
- TokenInfo
- JwtTokenProvider


3. Member Dto 추가 (Commit [59bdc4c](https://github.com/dameun0527/Assignment_Todo_developed/commit/59bdc4c80616081aa12a1a108e76b6f5d2180b35))
- DeleteRequestDto
- SignupRequestDto
- UpdateRequestDto


4. Member CRUD 기능 중 RUD 기능 코드 리펙토링 (Commit [c0acb7e](https://github.com/dameun0527/Assignment_Todo_developed/commit/c0acb7e863bb4125ec989c45651885a244cfd7bb))
- 불필요한 import문, 주석, 여백 등 삭제
- 수정한 필드명(id)으로 인한 에러 해결
- dto 점검 및 수정
- Postman으로 테스트 불가능: Create 기능 없어서



### 2024.10.29.

1. 전체 기능을 위한 Member Create 기능 임시로 구현 (Commit [c65ac4b](https://github.com/dameun0527/Assignment_Todo_developed/commit/c65ac4b9380764f1b8733ecda3a1a420156187a8))
- BaseTime (JPA Auditing 담당)과의 상속 삭제: 필요 없는 것 같아서


2. Member entity와 Schedule entity의 연관관계 변경 (Commit [d80b04f](https://github.com/dameun0527/Assignment_Todo_developed/commit/d80b04facc3911098d541dc4c724d0385f1cd3c5))
- ManyToMany -> OneToMany

3. Schedule CRUD 중 Create 기능 구현 및 에러 해결 (Commit [7f06a67](https://github.com/dameun0527/Assignment_Todo_developed/commit/7f06a67eb8dd62ed6fc9fb5732c914b93a7025fc))
- ScheduleController: 일정 생성(@PostMapping) 외 일시적 주석 처리
- Schedule dto 수정
  - ScheduleDto 상속 클래스 삭제 -> 나머지 클래스 수정사항 반영
  - ResponseDto 삭제 후 ScheduleResponseDto 추가
- Schedule 엔티티의 Id값 필드 이름 'scheduleId'로 변경: 일정관리 앱의 중심은 Member라서 Member의 id값을 'id'로 하기로 함.
- 연관관계 변경(2번)에 따른 Schedule entity 내의 코드 변경
  - @ManyToMany 삭제 -> @ManyToOne 추가
  - @Builder 수정
- #### 필드명 변경으로 인한 에러 발생
  - 해결을 위한 방법으로 @Slf4j 사용 ([블로그](https://jisuryu0527.tistory.com/63)에 로깅(Logging)과 @Slf4j에 대해 정리 예정)
  - 에러 출력: IllegalArgumentException: The given id must not be null
    - 발생 위치: `public Schedule save(CreateRequestDto createRequestDto)`
    - 원인: Schedule 생성 로직 내에 MemberId가 필요한 부분이 있는데, 당시 데이터 베이스 내에 있는 테이블을 드랍한 상태라 member 데이터가 존재하지 않았음. 그래서 불러올 memberId 값 자체가 존재하지 않음.
- sql 쿼리 주석 제거


4. Schedule CRUD 기능 구현 완료 + 기타 코드 수정 (Commit [b28ffe7](https://github.com/dameun0527/Assignment_Todo_developed/commit/b28ffe71423804cb2d5ae62f5fcfc14923c0fe9e))
- MemberController: 전체 Controller 코드 양식 획일화를 위해 일부 수정
- 페이지 기능 코드 주석화
- ScheduleResponseDto에 JPA Auditing data 전달을 위해 필드 추가(createdAt, updatedAt)
- BaseTime 상속 클래스
  - 필드 타입 변경: TimeStamp -> LocalDateTime
  - 출력 형식 조건 메서드 DateTimeFormatter로 패턴 추가: "yyyy년 MM월 dd일 HH시 mm분"
- Schedule 엔티티 내의 update 메서드 수정 -> updateScheduleInfo()
- ScheduleRepository 내에 최초 등록일 기준 최신 순 정렬 기능 추가
- ScheduleService: 각 기능별 비즈니스 로직 구현 및 획일화




### 2024.10.30


1. Comment CRUD 구현 완료 (Commit [e1d3178](https://github.com/dameun0527/Assignment_Todo_developed/commit/e1d3178628745206656bea7d4788c32c66b407f6))
- Comment 엔티티
  - 필드명 수정: id, content
  - 필드 추가: author
  - Member 엔티티와의 연관관계 삭제 -> 참조할 id값을 담을 필드 추가: memberId
  - 생성자 수정
- CommentService: CRUD 로직 수정 및 추가
- Schedule 엔티티: scheduleId -> id 로 수정
  - Member가 중심이라고 생각했지만 그것과는 아무 관계가 없다는 것을 알았음 -> convention에 따라 모든 엔티티 내의 고유 식별자는 id로 통일

<details>
<summary> id? memberId? 이렇게 이해하자. </summary>
`id`: 데이터베이스와 엔티티가 매핑될 수 있게하는 기본키 값
'memberId': 가상의 메모리에 클라이언트가 컨트롤러로 보낸 값 (그게 memberId에 해당하면 memberId인거고, scheduleId에 해당하면 scheduleId인 것)
-> 사용자가 입력한, 혹은 조회를 원하는 input 값을 컨트롤러가 받아서 dto를 통해 Repository로 전달 -> Repository에서 엔티티를 거쳐 일치하는 'id'값이 있는지 확인 -> 존재한다면 해당 'id'값 호출 or 없다면 IllegalArgumentException으로 던짐

</details>

  - -Id값 변경에 따라 다른 getter등의 접근자 메서드도 수정
- Dto: 이름 리펙토링, 다른 로직 실행에 필요한 생성자 추가 


2. .properties -> .yml로 변경 (Commit [c625d8d](https://github.com/dameun0527/Assignment_Todo_developed/commit/c625d8d2cfcb60bf2e32a231f276941126720a02))


3. JWT Token 관련 Dependency 추가 (Commit [3c6ccf1](https://github.com/dameun0527/Assignment_Todo_developed/commit/3c6ccf15c5f823db2293a92d00a4142be17a19ba))


4. Spring Security 관련 Dependency 추가 (Commit [26746d6](https://github.com/dameun0527/Assignment_Todo_developed/commit/26746d641b95eb3af0fafe277d01c2fc78521a91))
- application.yml: issuer, secret-key 발급
- LoginController 클래스 추가
- TokenInfo 클래스 이동 -> 새 패키지 생성(token)


5. JWT Token - Authentication 기능을 위한 코드 작성 (미완) (Commit [d177873](https://github.com/dameun0527/Assignment_Todo_developed/commit/d1778732a856f58567fb104d76a49796be035b2a))
- auth 패키지 내에 exception 패키지 추가, TokenException 클래스 추가
- JwtProperties -> issuer와 secretKey mapping (`@ConfigurationProperties("jwt")`)
- JwtTokenProvider 작성
  - 토큰 생성 메서드 `makeToken()`
  - 토큰 검증 메서드 `validToken()`
  - 토큰 인증 메서드 `getAuthentication`
  - 토큰 확인 후 정보 전달 메서드 `getUserId`, `getClaims`
- JwtAuthFilter 작성 (아직 완전히 이해 못함 / 개인적으로 공부한 책의 코드를 많이 인용했음)
  - Authentication 키 값 조회
  - 접두사 제거
  - 토큰 유효성 확인 후 인증 정보 설정


6. JWT Token - refreshToken 관련 기능을 위한 코드 작성 (미완) (Commit [3236b2f](https://github.com/dameun0527/Assignment_Todo_developed/commit/3236b2f80e17174aa8e144d26b90b1962b6b8a1b))
   
- 📚 RefreshToken에 대한 개념 이해가 필요함: accessToken이 만료되면 일일이 로그인으로 돌아가지 않고 새 refreshToken을 발급해서 로그인 상태를 유지한다?는 의미로 대충 이해함.
- Users 엔티티 작성
  - 이해하지 못한 부분:
    - 찾아보니 실제 데이터 베이스로서의 기능을 하는 엔티티가 아닌, Member 엔티티를 감싸는 엔티티라고 함
    - 일부 메서드 이름으로 봐선 권한 인증을 받은 사용자의 데이터를 가져오는 역할을 하는 것으로 보임


7. RefreshToken 관련 클래스 추가 및 로직 구현 (Commit [026a156](https://github.com/dameun0527/Assignment_Todo_developed/commit/026a156bea9c3db4293997b5997af09418b10fcf#diff-0b669de74ebc1f10b39159437d061a6e82bdb426747fa732c2d3a13c901b9120R5))
- RefreshToken
  - RefreshTokenRepository 추가
    - JpaRepository
    - `findByMemberId()`, `findByRefreshToken()` 추가
  - RefreshTokenService 추가
  

### 2024.10.31.

1. 프로젝트 전체 구조 개편 (Commit [e12811b](https://github.com/dameun0527/Assignment_Todo_developed/commit/e12811bd452c3a8cedef9c6dd56d06d004d9a572))
- model 패키지 새로 추가(src/main/java/com/sparta/assignment_todo_developed.model)
  - dto 패키지 + entity 패키지 model 패키지 내로 이동


2. Dependencies 정리 및 수정 (Commit [3b5cdcc](https://github.com/dameun0527/Assignment_Todo_developed/commit/3b5cdcc8538ddfc0dbaa6d888c6a23dfd985f359))
- jwt 관련 implementation 최신 버전으로 변경
- Spring Security 관련 Dependency 추가


3. 코드 정리 (Commit [e0193f1](https://github.com/dameun0527/Assignment_Todo_developed/commit/e0193f11e5973edd3ec68bee201d3ae50296e72d))
- 불필요한 import문 / 주석 / 여백 제거
- Service Layer 클래스 분할 -> business / technical


4. 코드 정리 (Commit [2e8a042](https://github.com/dameun0527/Assignment_Todo_developed/commit/2e8a042e2a654e421c4e81ccc00933bc85f51f19))
- 파일 이동 및 정리로 인한 변경사항 확인 및 정리


5. Refresh/Access token 관련 기능에 필요한 클래스 작성 (Commit [03b3c25](https://github.com/dameun0527/Assignment_Todo_developed/commit/03b3c255dafd50cc0d43742f05786b0d1dcbcb19))
- TokenApiController: token 발행 기능
- TokenService: access token 발행, refresh token 발행
- CreateAccessTokenRequest / CreateAccessTokenResponse: dto