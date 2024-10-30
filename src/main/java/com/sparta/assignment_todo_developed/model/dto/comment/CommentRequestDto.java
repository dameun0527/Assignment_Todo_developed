package com.sparta.assignment_todo_developed.model.dto.comment;

import com.sparta.assignment_todo_developed.model.entity.Comment;
import com.sparta.assignment_todo_developed.model.entity.Schedule;
import lombok.Getter;


@Getter
public class CommentRequestDto {

    private Long scheduleId;
    private String author;
    private Long memberId;
    private String content;


    public Comment toEntity(Schedule schedule) {
        return Comment.builder()
                .content(content)
                .memberId(memberId)
                .author(author)
                .schedule(schedule)
                .build();
    }

}
