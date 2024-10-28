package com.sparta.assignment_todo_developed.dto.member;

import lombok.Getter;

// 회원 삭제(탈퇴) 요청
@Getter
public class DeleteRequestDto {
    private String password;
}
