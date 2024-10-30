package com.sparta.assignment_todo_developed.service;

import com.sparta.assignment_todo_developed.entity.RefreshToken;
import com.sparta.assignment_todo_developed.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("토큰이 존재하지 않습니다."));
    }
}
