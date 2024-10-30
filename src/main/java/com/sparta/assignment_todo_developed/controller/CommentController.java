package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.model.dto.comment.CommentRequestDto;
import com.sparta.assignment_todo_developed.model.dto.comment.CommentResponseDto;
import com.sparta.assignment_todo_developed.service.business.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/schedule/{scheduleId}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto createdComment = commentService.createComment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long scheduleId) {
        List<CommentResponseDto> responseDtoList = commentService.getComment(scheduleId);
        return ResponseEntity.ok().body(responseDtoList);
    }

    // 댓글 수정 (내용 수정만 가능)
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto updatedCommentId = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto));
    }


    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
