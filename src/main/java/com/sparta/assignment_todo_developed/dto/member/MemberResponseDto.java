package com.sparta.assignment_todo_developed.dto.member;

import lombok.Getter;

@Getter

public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String email;

    public MemberResponseDto(Long memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }
}
