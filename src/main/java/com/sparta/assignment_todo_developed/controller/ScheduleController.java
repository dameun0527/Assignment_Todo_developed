package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.dto.schedule.ResponseDto;
import com.sparta.assignment_todo_developed.dto.schedule.UpdateRequestDto;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping()
    public ResponseEntity<ResponseDto> createSchedule(@RequestBody CreateRequestDto createRequestDto) {
        Schedule schedule = scheduleService.save(createRequestDto);
        ResponseDto responseDto = new ResponseDto(schedule.getId(), schedule.getTitle(), schedule.getUsername(), schedule.getContent());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping("/list")
    public ResponseEntity<List<ResponseDto>> findAllSchedules() {
        List<Schedule> schedules = scheduleService.findAll();
        List<ResponseDto> responseDtos = schedules.stream()
                .map(schedule -> new ResponseDto(schedule.getId(), schedule.getTitle(), schedule.getUsername(), schedule.getContent()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.findById(id);
        ResponseDto responseDto = new ResponseDto(schedule.getId(), schedule.getTitle(), schedule.getUsername(), schedule.getContent());
        return ResponseEntity.ok(responseDto);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateRequestDto updateRequestDto) {
        Schedule schedule = scheduleService.update(id, updateRequestDto);
        ResponseDto responseDto = new ResponseDto(schedule.getId(), schedule.getTitle(), schedule.getUsername(), schedule.getContent());
        return ResponseEntity.ok(responseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

