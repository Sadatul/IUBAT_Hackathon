package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.exceptions.OtpMismatchException;
import com.sadi.hackathonbase.exceptions.OtpTimedOutException;
import com.sadi.hackathonbase.repository.RefreshTokenRepository;
import com.sadi.hackathonbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class ResetPasswordService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public ResetPasswordService(UserRepository userRepository, RedisTemplate<String, String> redisTemplate, EmailService emailService, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Value("${FRONTEND_HOST}")
    private String frontendHost;

    @Value("${reset-password.prefix}")
    private String resetPasswordPrefix;

    @Value("${reset-password.timeout}")
    private int resetPasswordTimeout;

    @Value("${reset-password.email.body}")
    private String resetPasswordEmailBody;

    public void validateUsername(String username) {
        if(!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username " + username + " does not exist");
        }
    }

    public String getHashedToken(String token){
        return BCrypt.hashpw(token, BCrypt.gensalt(12));
    }

    public String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public void putPasswordToken(String email, String token){
        redisTemplate.opsForValue().set(resetPasswordPrefix + ":" + email, token, resetPasswordTimeout, TimeUnit.MINUTES);
    }

    public String getPasswordToken(String email){
        String token = redisTemplate.opsForValue().get(resetPasswordPrefix + ":" + email);
        if(token == null){
            throw new OtpTimedOutException("Token has timed out. Please try again");
        }
        return token;
    }

    public void resetPassword(String email, String password, String token){
        String hashedToken = getPasswordToken(email);
        if(!BCrypt.checkpw(token, hashedToken)){
            throw new OtpMismatchException("Token doesn't match");
        }
        userRepository.updatePasswordByUsername(email, passwordEncoder.encode(password));
        deletePasswordToken(email);
    }

    public void sendResetMail(String email, String token) {
        String body = String.format(resetPasswordEmailBody, frontendHost, email, token, resetPasswordTimeout);
        emailService.sendSimpleEmail(email, "Reset Password", body);
    }

    public void deletePasswordToken(String email){
        redisTemplate.delete(resetPasswordPrefix + ":" + email);
    }


    public void deleteRefreshTokens(String username) {
        Long userId = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " does not exist")).getId();
        refreshTokenRepository.deleteById(userId);
    }
}
