package com.sadi.hackathonbase.models.responses;

public record JwtTokenResponse(String token, String refreshToken, Long userId, String username) {
}
