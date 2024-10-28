package com.sparta.assignment_todo_developed.dto.member;

import com.sparta.assignment_todo_developed.entity.Member;
import lombok.Getter;

@Getter

public class MemberResponseDto {
    private final Long id;
    private final String email;
    private final String username;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.username = member.getUsername();


    }
}
