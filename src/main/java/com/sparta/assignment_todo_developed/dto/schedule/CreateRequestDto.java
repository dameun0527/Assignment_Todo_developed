package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class CreateRequestDto extends ScheduleDto {
    private String password;

    public CreateRequestDto(String title, Long memberId, String password, String content) {
        super(title, memberId, content);
        this.password = password;
    }
}