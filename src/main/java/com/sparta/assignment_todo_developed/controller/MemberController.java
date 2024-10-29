package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.dto.member.MemberRequestDto;
import com.sparta.assignment_todo_developed.dto.member.MemberResponseDto;
import com.sparta.assignment_todo_developed.dto.member.UpdateRequestDto;
import com.sparta.assignment_todo_developed.entity.Member;
import com.sparta.assignment_todo_developed.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;


    // 사용자 등록
    @PostMapping
    public ResponseEntity<MemberResponseDto> registerMember(@RequestBody MemberRequestDto requestDto) {
        MemberResponseDto memberResponseDto = memberService.createMembers(requestDto);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

//    @PostMapping()
//    public ResponseEntity<SchedulesResponseDto> createSchedules(@RequestBody SchedulesRequestDto schedulesRequestDto) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(schedulesService.createSchedules(schedulesRequestDto));
//    }

//    // 전체 사용자 목록 조회
//    @GetMapping
//    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
//        List<MemberResponseDto> members = memberService.getAllMembers();
//        return new ResponseEntity<>(members, HttpStatus.OK);
//    }

    // 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        MemberResponseDto responseDto = memberService.getMember(id);
        return ResponseEntity.ok(responseDto);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.updateMember(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 사용자 탈퇴 (삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>("탈퇴되셨습니다. 안녕히 가세요.", HttpStatus.NO_CONTENT);
    }
}