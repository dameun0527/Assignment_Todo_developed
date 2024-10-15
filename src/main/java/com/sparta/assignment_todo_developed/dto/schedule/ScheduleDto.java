package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ScheduleDto {

    // 공통 필드
    protected String title;
    protected String username;
    protected String content;
}
