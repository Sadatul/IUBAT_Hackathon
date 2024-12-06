package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.models.RefreshToken;
import com.sadi.hackathonbase.repository.RefreshTokenRepository;
import com.sadi.hackathonbase.utils.BasicUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${auth.jwt.refresh-token.timeout}")
    private int refreshTokenTimeout;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String getRefreshTokenForUser(Long userId) {
        String tokenValue = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken(userId, BasicUtils.generateSHA256Hash(tokenValue),
                Instant.now().plusSeconds(refreshTokenTimeout));
        refreshTokenRepository.save(refreshToken);
        return tokenValue;
    }

    public RefreshToken getRefreshTokenByToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(BasicUtils.generateSHA256Hash(token)).orElseThrow(
                () -> new BadCredentialsException("Invalid refresh token")
        );
        if (refreshToken.isExpired()) {
            throw new BadCredentialsException("Expired refresh token");
        }
        return refreshToken;
    }
}
