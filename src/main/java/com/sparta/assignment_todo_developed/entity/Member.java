package com.sparta.assignment_todo_developed.entity;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "Member")
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    public Member(MemberRequestDto memberRequestDto) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void updateMemberInfo(String password, String username) { // 비밀번호 + 사용자 이름(닉네임) 수정 기능
        this.password = password;
        this.username = username;
    }
}