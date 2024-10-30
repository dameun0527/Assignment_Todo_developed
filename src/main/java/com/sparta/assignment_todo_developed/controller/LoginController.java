package com.sparta.assignment_todo_developed.controller;

import com.sparta.assignment_todo_developed.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final MemberService memberService;

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login
}
