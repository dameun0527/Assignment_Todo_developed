package com.sparta.assignment_todo_developed.entity;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.dto.schedule.ScheduleDto;
import com.sparta.assignment_todo_developed.dto.schedule.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Schedule")
public class Schedule extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "content", nullable = false)
    private String content;

    // 일정 생성
    @Builder
    public Schedule(CreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    // 비밀번호 일치 여부 확인
    public boolean checkPassword(String inputPassword) {
        return inputPassword.equals(password);
    }

    @Builder
    public void update(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }
}
