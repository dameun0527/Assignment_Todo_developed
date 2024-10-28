package com.sparta.assignment_todo_developed.dto.member;

import lombok.Getter;

// 회원 정보 수정 요청
@Getter
public class UpdateRequestDto {
    private String password;
    private String username;
}
