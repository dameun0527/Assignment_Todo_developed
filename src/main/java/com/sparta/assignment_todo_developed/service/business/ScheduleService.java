package com.sparta.assignment_todo_developed.service.business;

import com.sparta.assignment_todo_developed.model.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.model.dto.schedule.ScheduleResponseDto;
import com.sparta.assignment_todo_developed.model.dto.schedule.UpdateRequestDto;
import com.sparta.assignment_todo_developed.model.entity.Member;
import com.sparta.assignment_todo_developed.model.entity.Schedule;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    // 일정 등록
    public Schedule save(CreateRequestDto createRequestDto) {
        // Member Id값 가져오기 전
        log.info("Requesting Member ID: {}", createRequestDto.getMemberId());

        Member member = memberRepository.findById(createRequestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        // Member Id값 가져오기 후
        log.info("Found Member: {}", member);
        Schedule schedule = Schedule.builder()
                .title(createRequestDto.getTitle())
                .content(createRequestDto.getContent())
                .member(member)
                .build();

        return scheduleRepository.save(schedule);
    }

    // 전체 일정 조회
    public List<Schedule> findAll() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc();
    }

    // 선택 일정 조회
    public Schedule findById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException(scheduleId + ": 존재하지 않는 일정입니다."));
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException(scheduleId + ": 존재하지 않는 일정입니다."));
        schedule.updateScheduleInfo(requestDto.getContent());
        return ScheduleResponseDto.from(scheduleRepository.save(schedule));

    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new IllegalArgumentException(scheduleId + ": 존재하지 않는 일정입니다."));
        scheduleRepository.delete(schedule);
    }
}
