package com.sparta.assignment_todo_developed.dto.comment;

import com.sparta.assignment_todo_developed.entity.Comment;
import com.sparta.assignment_todo_developed.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
