package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ResetPasswordReq {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=6, message = "Password must be at least 6 characters long.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.")
    private String password;

    @NotNull
    private String token;

    public ResetPasswordReq() {
    }

    public ResetPasswordReq(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 6, message = "Password must be at least 6 characters long.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 6, message = "Password must be at least 6 characters long.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.") String password) {
        this.password = password;
    }

    public @NotNull String getToken() {
        return token;
    }

    public void setToken(@NotNull String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResetPasswordReq{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
