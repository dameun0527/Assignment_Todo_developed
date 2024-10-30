package com.sparta.assignment_todo_developed.model.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

public class CreateRequestDto {
    private String title;
    private String content;
    private Long memberId;

}
