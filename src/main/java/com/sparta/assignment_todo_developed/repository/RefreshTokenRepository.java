package com.sparta.assignment_todo_developed.repository;

import com.sparta.assignment_todo_developed.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberId(Long memberId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
