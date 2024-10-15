package com.sparta.assignment_todo_developed.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestDto {
    private Long scheduleId;
    private String author;
    private String comment;

}
