package com.sadi.hackathonbase.models.dtos;

import com.sadi.hackathonbase.models.User;
import java.io.Serializable;

public class UnverifiedUser implements Serializable {
    private User user;
    private String otp;

    public UnverifiedUser(User user, String otp) {
        this.user = user;
        this.otp = otp;
    }

    public UnverifiedUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "UnverifiedUser{" +
                "user=" + user +
                ", otp='" + otp + '\'' +
                '}';
    }
}