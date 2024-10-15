package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.ScheduleDto;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping()
    public ResponseEntity<Schedule> addSchedule(@RequestBody ScheduleDto.CreateRequestDto request) {
        Schedule savedSchedule = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    // 전체 일정 조회
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleDto.ReadResponseDto>> findAllSchedules() {
        List<ScheduleDto.ReadResponseDto> schedules = scheduleService.findAll()
                .stream()
                .map(ScheduleDto.ReadResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(schedules);
    }

    // 선택 일정 조회
    @GetMapping("{id}")
    public ResponseEntity<ScheduleDto.ReadResponseDto> findSchedule(@PathVariable long id) {
        Schedule schedule = scheduleService.findById(id);

        return ResponseEntity.ok()
                .body(new ScheduleDto.ReadResponseDto(schedule));
    }

    // 일정 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable long id) {
        scheduleService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}

