package com.sparta.assignment_todo_developed.model.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRequestDto {
    private String title;
    private String content;
}