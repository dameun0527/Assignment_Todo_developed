package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.comment.RequestDto;
import com.sparta.assignment_todo_developed.dto.comment.ResponseDto;
import com.sparta.assignment_todo_developed.service.CommentService;
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
    public ResponseEntity<ResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody RequestDto requestDto) {
        ResponseDto createdComment = commentService.createComment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }
    // 댓글 조회
    @GetMapping
    public ResponseEntity<List<ResponseDto>> getCommentsBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getCommentBySchedule(scheduleId));
    }

    // 댓글 수정 (내용 수정만 가능)
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@PathVariable Long commentId, @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(scheduleId, commentId);
        return ResponseEntity.noContent().build();
    }
}
