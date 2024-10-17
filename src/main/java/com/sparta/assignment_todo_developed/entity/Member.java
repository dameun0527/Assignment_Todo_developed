package com.sparta.assignment_todo_developed.entity;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Member")
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", unique = true, nullable = false)
    private Long memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    public Member(MemberRequestDto memberRequestDto) {
        this.memberId = memberRequestDto.getMemberId();
        this.email = memberRequestDto.getEmail();
    }

    public void updateMember(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}