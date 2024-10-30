package com.sparta.assignment_todo_developed.model.dto.member;

import lombok.Getter;

// 회원 정보 수정 요청
@Getter
public class UpdateRequestDto {
    private String email;
    private String username;
    private String password;
}
