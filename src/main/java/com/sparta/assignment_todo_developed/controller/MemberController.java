package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import com.sparta.assignment_todo_developed.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.entity.Member;
import com.sparta.assignment_todo_developed.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 사용자 등록
    @PostMapping
    public ResponseEntity<MemberResponseDto> registerMember(@RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.save(memberRequestDto);
        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getId(), member.getName(), member.getEmail());
        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    // 전체 사용자 목록 조회
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.getMember(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.updateMember(id, memberRequestDto);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    // 사용자 탈퇴 (삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
