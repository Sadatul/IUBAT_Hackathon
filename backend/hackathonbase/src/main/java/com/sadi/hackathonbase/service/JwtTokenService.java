package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.responses.JwtTokenResponse;
import com.sadi.hackathonbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;
    private final RefreshTokenService refreshTokenService;

    @Value("${auth.jwt.audiences}")
    private String[] audiences;

    @Value("${auth.jwt.timeout}")
    private int timeout;

    @Value("${auth.jwt.issuer}")
    private String issuer;
    private final UserRepository userRepository;

    public JwtTokenService(JwtEncoder jwtEncoder, RefreshTokenService refreshTokenService,
                           UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    public JwtTokenResponse generateToken(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new UsernameNotFoundException(authentication.getName())
        );
        var claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(Instant.now())
                .audience(List.of(audiences))
                .expiresAt(Instant.now().plusSeconds(timeout))
                .subject(user.getId().toString())
                .build();
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        String refreshToken = refreshTokenService.getRefreshTokenForUser(user.getId());
        return new JwtTokenResponse(token, refreshToken, user.getId(), user.getUsername());
    }

    public String getJwtTokenFromUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(Instant.now())
                .audience(List.of(audiences))
                .expiresAt(Instant.now().plusSeconds(timeout))
                .subject(user.getId().toString())
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}