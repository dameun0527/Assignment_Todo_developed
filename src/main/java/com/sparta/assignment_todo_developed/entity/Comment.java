package com.sparta.assignment_todo_developed.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long commentId;

    // 댓글 작성자
    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    private Schedule schedule;

    @Builder
    public Comment(Schedule schedule, String author, String comment) {
        this.schedule = schedule;
        this.author = author;
        this.comment = comment;
    }

    // 댓글 수정
    public void updateComment(String comment) {
        this.comment = comment;
    }

}