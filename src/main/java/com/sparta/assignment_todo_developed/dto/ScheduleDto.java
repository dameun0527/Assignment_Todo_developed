package com.sparta.assignment_todo_developed.dto;

import com.sparta.assignment_todo_developed.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class ScheduleDto {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class CreateRequestDto {
        private final String title;
        private final String username;
        private final String password;
        private final String content;

        public Schedule toEntity() {
            return Schedule.builder()
                    .title(title)
                    .username(username)
                    .password(password)
                    .content(content)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

    }


    @AllArgsConstructor
    @Getter
    @Builder
    public static class ReadResponseDto {
        private final Long id;
        private final String title;
        private final String username;
        private final String content;
        private final String createdAt;
        private final String updatedAt;

        public ReadResponseDto(Schedule schedule) {
            this.id = schedule.getId();
            this.title = schedule.getTitle();
            this.username = schedule.getUsername();
            this.content = schedule.getContent();
            this.createdAt = schedule.getCreatedAt().toString();
            this.updatedAt = schedule.getUpdatedAt().toString();
        }
    }

    @AllArgsConstructor
    @Getter
    public static class UpdateRequestDto {
        private final String title;
        private final String username;
        private final String password;
        private final String content;
        private final String updatedAt;

    }


}
