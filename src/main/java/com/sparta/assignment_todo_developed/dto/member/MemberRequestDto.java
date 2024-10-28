package com.sparta.assignment_todo_developed.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
}
