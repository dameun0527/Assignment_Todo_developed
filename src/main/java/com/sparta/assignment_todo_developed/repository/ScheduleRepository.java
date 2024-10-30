package com.sparta.assignment_todo_developed.repository;

import com.sparta.assignment_todo_developed.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc();

    default Schedule findScheduleById(Long id) {
        return findById(id).orElseThrow(()-> new RuntimeException("존재하지 않는 일정입니다."));
    }
}
