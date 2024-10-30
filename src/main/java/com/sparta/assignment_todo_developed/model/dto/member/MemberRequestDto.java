package com.sparta.assignment_todo_developed.model.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class MemberRequestDto { // 회원가입 요청, 사용자 조회, 사용자 탈퇴
    private String email;
    private String password;
    private String username;
}
