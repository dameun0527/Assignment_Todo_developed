package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.ScheduleDto;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 일정 등록
    public Schedule save(ScheduleDto.CreateRequestDto createRequestDto) {
        return scheduleRepository.save(createRequestDto.toEntity());

    }

    // 일정 목록 조회
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    // 선택 일정 조회
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id +": 존재하지 않는 일정입니다."));
    }

    // 일정 수정
    @Transactional
    public Schedule update(long id, ScheduleDto.UpdateRequestDto updateRequestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(id +": 존재하지 않는 일정입니다."));

        schedule.update(updateRequestDto.getTitle(), updateRequestDto.getUsername())
    }


    // 일정 삭제
    public void delete(long id) {
        scheduleRepository.deleteById(id);
        System.out.println("삭제되었습니다.");
    }
}
