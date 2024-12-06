package com.sadi.hackathonbase.exceptions;

public class BadRequestFromUserException extends RuntimeException {
    public BadRequestFromUserException(String message) {
        super(message);
    }
}
