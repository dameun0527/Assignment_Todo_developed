package com.sparta.assignment_todo_developed.dto.schedule;

import com.sparta.assignment_todo_developed.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private Long memberId;
    private String createdAt;
    private String updatedAt;





    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .scheduleId(schedule.getScheduleId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .memberId(schedule.getMember().getId())
                .createdAt(schedule.getFormattedCreatedAt())
                .updatedAt(schedule.getFormattedUpdatedAt())
                .build();
    }

}