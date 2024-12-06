package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.NotNull;


public class UserVerificationRequest {
    @NotNull
    private String username;
    @NotNull
    private String otp;

    public UserVerificationRequest(String username, String otp) {
        this.username = username;
        this.otp = otp;
    }

    public UserVerificationRequest() {
    }

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public @NotNull String getOtp() {
        return otp;
    }

    public void setOtp(@NotNull String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "UserVerificationRequest{" +
                "username='" + username + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}