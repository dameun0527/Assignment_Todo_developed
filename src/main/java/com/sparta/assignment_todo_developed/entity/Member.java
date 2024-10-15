package com.sparta.assignment_todo_developed.entity;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private Set<Schedule> schedules;

    public Member(MemberRequestDto memberRequestDto) {
        this.name = memberRequestDto.getName();
        this.email = memberRequestDto.getEmail();
    }

    public void updateMember(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
