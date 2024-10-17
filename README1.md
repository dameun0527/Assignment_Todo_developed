# Assignment_Todo_developed

1. ERD와 API 명세서 작성 (Lv 0)

   ERD (Entity-Relationship Diagram): 데이터베이스 구조 설계를 먼저 진행하세요. 주요 엔티티는 User, Schedule, Comment입니다.
   User: 유저명, 비밀번호, 권한 등의 정보를 저장합니다.
   Schedule: 할 일의 제목, 내용, 작성일, 수정일 등을 저장합니다.
   Comment: 스케줄과 연관된 댓글 정보를 저장합니다.

   연관 관계:
   한 유저는 여러 개의 스케줄을 가질 수 있습니다 (OneToMany).
   한 스케줄은 여러 개의 댓글을 가질 수 있으며 (OneToMany), 각 댓글은 하나의 스케줄에 속합니다 (ManyToOne).
   API 명세서 작성: 스케줄, 댓글, 유저에 대한 CRUD 엔드포인트를 정의하고 각 요청에 필요한 필드와 HTTP 메서드(GET, POST, PUT, DELETE)를 명시하세요.


2. JPA 엔티티 정의 (Lv 1 & Lv 2)

   엔티티 클래스 생성: User, Schedule, Comment 엔티티 클래스를 생성합니다.
   각 클래스에 @Entity와 @Id를 사용하여 데이터베이스 테이블과 매핑합니다.
   Schedule 클래스에 유저명, 할일 제목, 내용, 작성일, 수정일 필드를 정의하세요.
   댓글을 저장하기 위해 Comment 클래스에 ManyToOne 관계를 설정하고, 댓글의 내용과 작성일 필드를 추가하세요.
   영속성 전이(Cascade) 설정: 스케줄 삭제 시 관련 댓글도 함께 삭제되도록 CascadeType.ALL을 설정하세요.


3. Service, Repository, Controller 구현 (3 Layer Architecture)

   Service Layer: 비즈니스 로직을 처리하는 ScheduleService, CommentService, UserService 클래스를 만듭니다.
   스케줄 생성, 조회, 수정, 삭제 등의 로직을 구현합니다.
   Repository Layer: JpaRepository를 상속받아 각 엔티티의 데이터베이스 접근을 처리하는 리포지토리를 생성합니다.
   Controller Layer: RESTful API 요청을 처리하는 ScheduleController, CommentController, UserController를 만듭니다.


4. JPA Auditing 적용 (작성일, 수정일 필드) (Lv 1)

   @CreatedDate, @LastModifiedDate를 사용하여 작성일과 수정일을 자동으로 관리합니다.
   JPA Auditing을 활성화하기 위해 @EnableJpaAuditing을 설정하세요.


5. 댓글 CRUD 구현 (Lv 2)

   댓글은 스케줄과 연관 관계를 가지므로 Comment 엔티티에 @ManyToOne 관계를 설정합니다.
   댓글 작성, 조회, 수정, 삭제 기능을 구현합니다.


6. 페이징 처리 구현 (Lv 3)

   Pageable과 PageRequest를 사용하여 스케줄 조회 시 페이징을 구현합니다.
   스케줄의 수정일을 기준으로 내림차순 정렬하여 보여줍니다.


7. 유저 CRUD 및 연관 관계 설정 (Lv 4)

   유저와 스케줄 간의 N
   관계를 설정하고, 유저 정보와 함께 스케줄 정보를 관리할 수 있도록 구현합니다.
   각 유저는 스케줄을 담당할 수 있으며, 해당 정보를 스케줄에 저장합니다.


8. JWT를 활용한 인증 및 인가 (도전 기능 Lv 1 ~ Lv 3)

   JWT 기반 로그인/회원가입: 유저 생성 시 JWT를 발급하고, 로그인 시 토큰을 검증하는 기능을 구현합니다.
   Spring Security와 JWT를 활용하여 권한에 따른 접근을 제어합니다.


9. 외부 API 연동 (도전 기능 Lv 4)

   RestTemplate을 사용하여 외부 API (예: 날씨 정보)를 조회하고, 일정에 해당 정보를 저장합니다.