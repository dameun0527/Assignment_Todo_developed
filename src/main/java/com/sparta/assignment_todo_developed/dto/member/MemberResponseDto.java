package com.sparta.assignment_todo_developed.dto.member;

import lombok.Getter;

@Getter

public class MemberResponseDto {
    private Long id;
    private Long memberId;
    private String email;

    public MemberResponseDto(Long id, Long memberId, String email) {
        this.id = id;
        this.memberId = memberId;
        this.email = email;
    }
}
