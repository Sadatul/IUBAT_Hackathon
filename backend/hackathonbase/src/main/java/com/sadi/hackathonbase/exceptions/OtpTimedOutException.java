package com.sadi.hackathonbase.exceptions;

public class OtpTimedOutException extends RuntimeException{
    public OtpTimedOutException(String message) {
        super(message);
    }
}
