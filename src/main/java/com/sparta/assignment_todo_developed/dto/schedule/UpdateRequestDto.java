package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UpdateRequestDto extends ScheduleDto {

    private String password;

    public UpdateRequestDto(String title, String password, String username, String content) {
        super(title, username, content);
        this.password = password;
    }
}
