package com.sparta.assignment_todo_developed.model.dto.member;

import lombok.Getter;

@Getter
// 회원 가입 요청
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;
}
