package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.dto.schedule.ScheduleResponseDto;
import com.sparta.assignment_todo_developed.dto.schedule.UpdateRequestDto;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
import com.sparta.assignment_todo_developed.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @PostMapping()
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody CreateRequestDto createRequestDto) {
        Schedule schedule = scheduleService.save(createRequestDto);
        ScheduleResponseDto responseDto = ScheduleResponseDto.from(schedule);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.findAll()
                .stream()
                .map(ScheduleResponseDto::from)
                .toList();
        return ResponseEntity.ok(schedules);
    }

//    @GetMapping("/list")
//    public Page<Schedule> getAllSchedules(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) String keyword) {
//        // Pageable 객체 생성
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updatedAt")));
//        return scheduleRepository.findAll(pageable);
//    }
//
    // 선택 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        return ResponseEntity.ok(ScheduleResponseDto.from(schedule));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
