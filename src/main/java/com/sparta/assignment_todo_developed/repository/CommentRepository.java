package com.sparta.assignment_todo_developed.repository;

import com.sparta.assignment_todo_developed.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
