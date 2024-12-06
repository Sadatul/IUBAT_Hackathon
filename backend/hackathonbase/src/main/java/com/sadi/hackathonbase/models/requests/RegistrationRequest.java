package com.sadi.hackathonbase.models.requests;

import com.sadi.hackathonbase.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RegistrationRequest {

    @Email
    private String username;

    @Size(min=6, message = "Password must be at least 6 characters long.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{2,}$", message = "A password must include at least one number and one special character.")
    private  String password;

    @NotNull(message = "User must have a role")
    private String fullName;

    @NotNull(message = "User must have a date of birth")
    private LocalDate dob;

    @NotNull(message = "User must have a gender")
    private Gender gender;


    public RegistrationRequest(String username, String password, String fullName, LocalDate dob, Gender gender) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
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

    public @NotNull(message = "User must have a date of birth") LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull(message = "User must have a date of birth") LocalDate dob) {
        this.dob = dob;
    }

    public @NotNull(message = "User must have a gender") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "User must have a gender") Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                '}';
    }
}