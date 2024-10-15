package com.sparta.assignment_todo_developed.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
}
