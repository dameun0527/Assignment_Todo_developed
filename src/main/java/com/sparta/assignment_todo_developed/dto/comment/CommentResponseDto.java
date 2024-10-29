package com.sparta.assignment_todo_developed.dto.comment;

import com.sparta.assignment_todo_developed.entity.Comment;
import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentResponseDto {

    private Long scheduleId;
    private String author;
    private Long memberId;
    private String content;
    private Long id;
    private String createdAt;
    private String updatedAt;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.scheduleId = comment.getSchedule().getId();
        this.author = comment.getAuthor();
        this.memberId = comment.getMemberId();
        this.createdAt = comment.getFormattedCreatedAt();
        this.updatedAt = comment.getFormattedUpdatedAt();

    }
}
