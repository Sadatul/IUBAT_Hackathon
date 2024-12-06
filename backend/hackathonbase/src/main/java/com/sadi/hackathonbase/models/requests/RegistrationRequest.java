package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {

    @Email
    private String username;

    @Size(min=6, message = "Password must be at least 6 characters long.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.")
    private  String password;

    @NotNull(message = "User must have a role")
    private String fullName;

    public RegistrationRequest(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public RegistrationRequest() {
    }

    public @Email String getUsername() {
        return username;
    }

    public void setUsername(@Email String username) {
        this.username = username;
    }

    public @Size(min = 6, message = "Password must be at least 6 characters long.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Password must be at least 6 characters long.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.") String password) {
        this.password = password;
    }

    public @NotNull(message = "User must have a role") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull(message = "User must have a role") String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}