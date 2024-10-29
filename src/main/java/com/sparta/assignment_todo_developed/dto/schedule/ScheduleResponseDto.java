package com.sparta.assignment_todo_developed.dto.schedule;

import com.sparta.assignment_todo_developed.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ScheduleResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private Long memberId;


    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .scheduleId(schedule.getScheduleId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .memberId(schedule.getMember().getId())
                .build();
    }

}