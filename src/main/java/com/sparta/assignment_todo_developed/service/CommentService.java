package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.comment.RequestDto;
import com.sparta.assignment_todo_developed.dto.comment.ResponseDto;
import com.sparta.assignment_todo_developed.entity.Comment;
import com.sparta.assignment_todo_developed.entity.Member;
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
    public ResponseDto createComment(RequestDto requestDto) {
        // scheduleId로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException(requestDto.getScheduleId() + ": 존재하지 않는 일정입니다."));

        // memberId로 해당 멤버 찾기
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException(requestDto.getMemberId() + ": 존재하지 않는 사용자입니다."));

        // 댓글 생성
        Comment comment = Comment.builder()
                .schedule(schedule)
                .member(member)
                .comment(requestDto.getComment())
                .build();

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);
        return new ResponseDto(savedComment);
    }

    // 댓글 조회
    @Transactional
    public List<ResponseDto> getCommentBySchedule(Long scheduleId) {
        return commentRepository.findById(scheduleId).stream()
                .map(comment -> new ResponseDto(comment))
                .collect(Collectors.toList());
    }

    // 댓글 수정 (내용만 가능)
    @Transactional
    public ResponseDto updateComment(Long commentId, RequestDto requestDto) {
        // commentId로 댓글 찾기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 댓글 내용 수정
        comment.updateComment(requestDto.getComment());

        // 수정된 댓글 반환
        return new ResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, Long scheduleId) {
        // commentId로 댓글 찾기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
