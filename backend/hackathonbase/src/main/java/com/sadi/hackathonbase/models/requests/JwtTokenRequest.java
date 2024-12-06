package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.NotNull;

public class JwtTokenRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public JwtTokenRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public JwtTokenRequest() {
    }

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtTokenRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
