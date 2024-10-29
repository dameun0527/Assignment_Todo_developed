package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.comment.CommentRequestDto;
import com.sparta.assignment_todo_developed.dto.comment.CommentResponseDto;
import com.sparta.assignment_todo_developed.entity.Comment;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.repository.CommentRepository;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    // 댓글 생성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        // scheduleId로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException(requestDto.getScheduleId() + ": 존재하지 않는 일정입니다."));

//        // memberId로 해당 멤버 찾기
//        Member member = memberRepository.findById(requestDto.getMemberId())
//                .orElseThrow(() -> new IllegalArgumentException(requestDto.getMemberId() + ": 존재하지 않는 사용자입니다."));

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .memberId(requestDto.getMemberId())
                .author(requestDto.getAuthor())
                .schedule(schedule)
                .build();

        comment = commentRepository.save(comment);
        return new CommentResponseDto(comment);

    }

    // 댓글 조회
    @Transactional
    public List<CommentResponseDto> getComment(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);
        return schedule.getComments().stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    // 댓글 수정 (내용만 가능)
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        // commentId로 댓글 찾기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        // 댓글 내용 수정
        comment.updateComment(requestDto.getContent());
        // 수정된 댓글 반환
        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        // commentId로 댓글 찾기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
