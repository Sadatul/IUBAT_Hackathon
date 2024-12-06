package com.sadi.hackathonbase.exceptions;

public class OtpMismatchException extends RuntimeException{
    public OtpMismatchException(String message) {
        super(message);
    }
}
