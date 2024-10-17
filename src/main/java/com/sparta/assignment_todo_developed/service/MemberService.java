package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import com.sparta.assignment_todo_developed.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.entity.Member;
import com.sparta.assignment_todo_developed.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 등록
    public Member save(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto);
        return memberRepository.save(member);
    }


    // 전체 사용자 목록 조회
    public List<MemberResponseDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getMemberId(), member.getEmail()))
                .toList();
    }

    // 특정 사용자 조회
    public MemberResponseDto getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return new MemberResponseDto(member.get().getId(), member.get().getMemberId(), member.get().getEmail());
        } else {
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
        }
    }

    // 사용자 정보 수정
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member existingMember = memberOptional.get();

            // updateMember 메서드를 사용하여 값을 변경
            existingMember.updateMember(memberRequestDto.getMemberId(), memberRequestDto.getEmail());

            // 변경된 엔티티를 저장
            existingMember = memberRepository.save(existingMember);

            // 업데이트된 정보 반환
            return new MemberResponseDto(existingMember.getId(), existingMember.getMemberId(), existingMember.getEmail());
        } else {
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
        }
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