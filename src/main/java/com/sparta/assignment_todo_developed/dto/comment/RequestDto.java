package com.sparta.assignment_todo_developed.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestDto {
    private Long scheduleId;
    private Long memberId;
    private String comment;

}
