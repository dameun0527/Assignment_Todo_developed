package com.sparta.assignment_todo_developed.dto.schedule;

import lombok.Getter;

@Getter
public final class ResponseDto extends ScheduleDto {
    private Long id;

    // 응답 시 비밀번호 표시 X
    public ResponseDto(Long id, String title, Long memberId, String content) {
        super(title, memberId, content);
        this.id = id;
    }
}