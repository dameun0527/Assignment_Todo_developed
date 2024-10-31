package com.sparta.assignment_todo_developed.service.business;

import com.sparta.assignment_todo_developed.model.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.model.dto.member.SignupRequestDto;
import com.sparta.assignment_todo_developed.model.dto.member.UpdateRequestDto;
import com.sparta.assignment_todo_developed.model.entity.Member;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 사용자 등록: 회원 가입 기능 구현 후
    public Long save(SignupRequestDto dto) {
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();
        return memberRepository.save(member).getId();
    }


    // 특정 사용자 조회
    public MemberResponseDto getMember(Long id) {
        Member member = findById(id);
        return new MemberResponseDto(member);
        }


    // 사용자 정보 수정
    @Transactional
    public MemberResponseDto updateMember(Long memberId, UpdateRequestDto requestDto) {
        Member member = findById(memberId);
        member.updateMemberInfo(requestDto.getUsername(), requestDto.getPassword());
        return new MemberResponseDto(member);
    }

    // 사용자 탈퇴 (삭제)
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = findById(memberId);
        memberRepository.delete(member);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return new User(member.getEmail(), member.getPassword(), Collections.emptyList());
    }
}