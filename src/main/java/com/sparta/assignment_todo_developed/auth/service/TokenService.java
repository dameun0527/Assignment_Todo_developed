package com.sparta.assignment_todo_developed.auth.service;

import com.sparta.assignment_todo_developed.auth.token.JwtTokenProvider;
import com.sparta.assignment_todo_developed.model.entity.Member;
import com.sparta.assignment_todo_developed.service.business.MemberService;
import com.sparta.assignment_todo_developed.service.technical.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사 실패 시 예외 발생
        if(!jwtTokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        Long memberId = refreshTokenService.findByRefreshToken(refreshToken).getId();
        Member member = memberService.findById(memberId);

        return jwtTokenProvider.generateToken(member, Duration.ofHours(2));
    }
}
