package com.sparta.assignment_todo_developed.repository;

import com.sparta.assignment_todo_developed.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
