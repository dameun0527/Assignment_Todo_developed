package com.sparta.assignment_todo_developed.dto.schedule;

import com.sparta.assignment_todo_developed.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class CreateRequestDto extends ScheduleDto {
    private String password;

    public CreateRequestDto(String title, String username, String password, String content) {
        super(title, username, content);
        this.password = password;
    }
}
