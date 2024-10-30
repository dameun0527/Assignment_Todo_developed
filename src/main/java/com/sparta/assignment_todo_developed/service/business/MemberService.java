package com.sparta.assignment_todo_developed.service.business;

import com.sparta.assignment_todo_developed.auth.token.JwtTokenProvider;
import com.sparta.assignment_todo_developed.model.dto.member.MemberRequestDto;
import com.sparta.assignment_todo_developed.model.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.model.dto.member.UpdateRequestDto;
import com.sparta.assignment_todo_developed.model.entity.Member;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerMapping;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HandlerMapping resourceHandlerMapping;


    // 사용자 등록: 회원 가입 기능 구현 후
    public MemberResponseDto createMembers(MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember);
    }


    // 특정 사용자 조회
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        return new MemberResponseDto(member);
        }


    // 사용자 정보 수정
    @Transactional
    public MemberResponseDto updateMember(Long memberId, UpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow (() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        member.updateMemberInfo(requestDto.getUsername(), requestDto.getPassword());
        return new MemberResponseDto(member);
    }

    // 사용자 탈퇴 (삭제)
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        memberRepository.delete(member);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }
}