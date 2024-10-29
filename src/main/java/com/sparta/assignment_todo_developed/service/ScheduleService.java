package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.schedule.CreateRequestDto;
import com.sparta.assignment_todo_developed.entity.Member;
import com.sparta.assignment_todo_developed.entity.Schedule;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

//    // 일정 목록 조회
//    public Page<Schedule> getAllSchedules(Pageable pageable) {
//        return scheduleRepository.findAll(pageable);
//    }
//
//
//    // 선택 일정 조회
//    public Schedule findById(Long id) {
//        return scheduleRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(id + ": 존재하지 않는 일정입니다."));
//    }

//    //일정 수정
//    @Transactional
//    public Schedule update(long id, UpdateRequestDto updateRequestDto) {
//        Schedule schedule = scheduleRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException(id + ": 존재하지 않는 일정입니다."));
//
//        if (!updateRequestDto.getPassword().equals(schedule.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        schedule.update(updateRequestDto);
//        return scheduleRepository.save(schedule);
//    }

//    // 일정 삭제
//    public void delete(long id) {
//        if (!scheduleRepository.existsById(id)) {
//            throw new IllegalArgumentException(id + ": 존재하지 않는 일정입니다.");
//        }
//        scheduleRepository.deleteById(id);
//        System.out.println("삭제되었습니다.");
//    }
}
