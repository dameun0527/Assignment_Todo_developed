package com.sparta.assignment_todo_developed.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Getter
public class ResponseDto {
    private Long commentId;
    private String author;
    private String comment;


    public ResponseDto(Long commentId, String author, String comment) {
        this.commentId = commentId;
        this.author = author;
        this.comment = comment;

    }
}
