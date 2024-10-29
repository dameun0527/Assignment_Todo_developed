package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.dto.schedule.ScheduleResponseDto;
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
//    @GetMapping("/list")
//    public ResponseEntity<List<ResponseDto>> findAllSchedules() {
//        List<Schedule> schedules = scheduleService.findAll();
//        List<ResponseDto> responseDtos = schedules.stream()
//                .map(schedule -> new ResponseDto(schedule.getId(), schedule.getTitle(), schedule.getUsername(), schedule.getContent()))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseDtos);
//    }

    @GetMapping("/list")
    public Page<Schedule> getAllSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updatedAt")));
        return scheduleRepository.findAll(pageable);
    }

//    // 선택 일정 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDto> findSchedule(@PathVariable Long id) {
//        Schedule schedule = scheduleService.findById(id);
//        Long memberId = schedule.getMembers().get(0).getId();
//        ResponseDto responseDto = new ResponseDto(schedule.getId(), schedule.getTitle(), memberId, schedule.getContent());
//        return ResponseEntity.ok(responseDto);
//    }

//    // 일정 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateRequestDto updateRequestDto) {
//        Schedule schedule = scheduleService.update(id, updateRequestDto);
//        Long memberId = schedule.getMembers().get(0).getId();
//        ResponseDto responseDto = new ResponseDto(schedule.getId(), schedule.getTitle(), memberId, schedule.getContent());
//        return ResponseEntity.ok(responseDto);
//    }

//    // 일정 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
//        scheduleService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
