package com.sparta.assignment_todo_developed.model.dto.schedule;

import com.sparta.assignment_todo_developed.model.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String createdAt;
    private String updatedAt;





    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .memberId(schedule.getMember().getId())
                .createdAt(schedule.getFormattedCreatedAt())
                .updatedAt(schedule.getFormattedUpdatedAt())
                .build();
    }

}