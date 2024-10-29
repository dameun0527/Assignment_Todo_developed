# [Spring 3기] CH 3. 일정 관리 앱 만들기 ver. Developed


![image.jpg](image.jpg)

## 📁 과제 소개

이전 과제인 'Spring 입문 - 일정 관리 앱 만들기'를 기본으로 한 심화 과제.

입문에서 Jdbc를 다뤘다면 이번에는 JPA를 위주로 다룬다. 연관 관계 설정, cascade 전이 등을 통한 JPA의 주요 개념 이해와 실제 어플리케이션에 적용하는 방법을 배우는 것이 
주 목적이라고 할 수 있다. 

## 🌏 개발 환경
<br>


##### ✔  Project
- Project: Gradle - Groovy
- Language: Java
- Spring Boot: 3.3.4


##### ✔ Project Metadata
- Group: com.sparta
- Artifact: Assignment_Todo_developed
- Package name: com.sparta.assignment_todo_developed
- Packaging: Jar
- Java: zulu-17 java version "17.0.12"

##### ✔ Dependencies 
- Lombok
- Spring Web
- Thymeleaf
- Spring Data JPA
- MySQL Driver


## ⏲ 개발 기간

2024.10.07 ~ 2024.10.17.

## 📋 목차

1️⃣ 기획편 - 요구 사항 파악 및 정리

2️⃣ 개발편 - 단계별 필수 기능 구현을 위한 코딩

[필수 기능]

[도전 기능]

3️⃣ 고찰 및 회고 편 - 힘들거나 어려웠던 부분 및 소감

<br>

## 📌 본편
### 1️⃣ 기획편
[CH 3. 일정 관리 앱 ver. Developed - 기획편](https://jisuryu0527.tistory.com/57)

###  2️⃣ 개발편
**[필수 기능]**
- Lv 0. API 명세 및 ERD, SQL 작성 - Complete


**API 명세**

|      기능      | Method |        URL         | Request-Header                 | Request-Body                                                                                            | Response-code | Response-body                                                              |
|:------------:|:------:|:------------------:|--------------------------------|---------------------------------------------------------------------------------------------------------|:-------------:|:---------------------------------------------------------------------------|
|  **일정 생성**   |  POST  |   /api/schedule    | Content-Type: application/json | json {<br>"title": "할일",<br>"username": "작성자명",<br>"password": "비밀번호",<br>"content": "내용"<br>}          |  201: 정상 등록   | N/A                                                                        |
| **전체 일정 조회** |  GET   | /api/schedule/list | Content-Type: application/json | N/A                                                                                                     |  200: 정상 조회   |                                                                            |
| **선택 일정 조회** |  GET   | /api/schedule/{id} | Content-Type: application/json | N/A                                                                                                     |  200: 정상 조회   | {<br>"id": 1,<br>"title": "할일",<br>"name": "작성자명",<br>"content" "내용",<br>} |
| **선택 일정 수정** |  PUT   | /api/schedule/{id} | Content-Type: application/json | json{<br>"title": "수정된 할일",<br>"password": "비밀번호"<br>"content: "수정된 내용",<br>} |  204: 정상 수정   | N/A                                                                        |
| **선택 일정 삭제** | DELETE | /api/schedule/{id} | Content-Type: application/json | N/A                                                                                                     |  204: 정상 삭제   | N/A                                                                        |

- Lv 1. 일정 CRUD - Complete
- Lv 2. 댓글 CRUD - Complete
- Lv 3. 일정 페이징 조회 - Complete
- Lv 4. 유저 CRUD - Incomplete
- Lv 5. 다양한 예외처리 적용 - None

<br>

**entity package**

[Schedule.java]
<details>
<summary> 코드 전체 확인하기 </summary>

```java
package com.sparta.assignment_todo_developed.entity;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.dto.schedule.ScheduleResponseDto;
import com.sparta.assignment_todo_developed.dto.schedule.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
public class Schedule extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "member_schedule",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;



    @Builder
    public Schedule(String title, String content, String password, List<Member> members) {
        this.title = title;
        this.password = password;
        this.content = content;
        this.members = members;
    }

    public void update(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
```

</details>

✔ About Schedule.java



[Member.java]
<details>
<summary> 코드 전체 확인하기 </summary>

```java

```

</details>

✔ About Member.java



[Comment.java]
<details>
<summary> 코드 전체 확인하기 </summary>

```java
package com.sparta.assignment_todo_developed.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long commentId;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Comment(Schedule schedule, Member member, String comment) {
        this.schedule = schedule;
        this.member = member;
        this.comment = comment;
    }

    // 댓글 수정
    public void updateComment(String comment) {
        this.comment = comment;
    }

}
```
</details>


✔ About Comment.java


[BaseTime.java]
<details>
<summary> 코드 전체 확인하기 </summary>

```java
package com.sparta.assignment_todo_developed.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;
}

```
</details>

✔ About BaseTime.java

<br>

**dto package**

- schedule directory

[ScheduleDto.java]

<details>
<summary> 코드 전체 확인하기 </summary>

```java
package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ScheduleDto {

    // 공통 필드
    protected String title;
    protected Long memberId;
    protected String content;
}
```
</details>

✔ About ScheduleDto.java

<br>

[UpdateRequestDto.java]

<details>
<summary> 코드 전체 확인하기 </summary>

```java

```
</details>

✔








**[도전 기능]** - None
- Lv 1. 회원가입 (JWT)
- Lv 2. 로그인 (인증)
- Lv 3. 권한 확인 (인가)
- Lv 4. 외부 API 조회



###  3️⃣ 고찰 및 회고 편

**후기**


