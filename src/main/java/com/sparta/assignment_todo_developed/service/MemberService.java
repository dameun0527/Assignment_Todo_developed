package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import com.sparta.assignment_todo_developed.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.entity.Member;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 등록
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());

        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember.getMemberId(), savedMember.getName(), savedMember.getEmail());
    }


    // 전체 사용자 목록 조회
    public List<MemberResponseDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getMemberId(), member.getName(),member.getEmail()))
                .toList();
    }

    // 특정 사용자 조회
    public MemberResponseDto findMemberByName(String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("해당 사용자를 찾을 수 없습니다.")));
        return new MemberResponseDto(member.getMemberId(), member.getName(), member.getEmail());
        }
    }

    // 사용자 정보 수정
    public MemberResponseDto updateMember(Long memberId, String name) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow (() -> new MemberNotFound
    }

    // 사용자 탈퇴 (삭제)
    public void deleteMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
        } else {
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
        }
    }
}