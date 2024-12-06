package com.sadi.hackathonbase.models.responses;

import java.time.LocalDateTime;

public record ChatMessageRes(String message, Boolean isUser, LocalDateTime sentAt) {
}
