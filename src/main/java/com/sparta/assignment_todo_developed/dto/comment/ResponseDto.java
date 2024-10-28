package com.sparta.assignment_todo_developed.dto.comment;

import com.sparta.assignment_todo_developed.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseDto {
    private Long commentId;
    private Long scheduleId;
    private String comment;
    private Long memberId;


    public ResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.scheduleId = comment.getSchedule().getId();
        this.memberId = comment.getMember().getId();

    }
}
