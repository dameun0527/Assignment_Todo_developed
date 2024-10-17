package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UpdateRequestDto extends ScheduleDto {

    private String password;

    public UpdateRequestDto(String title, Long memberId, String password, String content) {
        super(title,memberId,content);
        this.password = password;
    }
}