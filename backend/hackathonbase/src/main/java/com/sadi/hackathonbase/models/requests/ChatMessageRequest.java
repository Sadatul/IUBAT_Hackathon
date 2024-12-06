package com.sadi.hackathonbase.models.requests;


public class ChatMessageRequest {
    private String message;
    private Long sessionId;
    private Boolean isUser;

    public ChatMessageRequest() {
    }

    public ChatMessageRequest(String message, Long sessionId, Boolean isUser) {
        this.message = message;
        this.sessionId = sessionId;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(Boolean user) {
        isUser = user;
    }

    @Override
    public String toString() {
        return "ChatMessageRequest{" +
                "message='" + message + '\'' +
                ", sessionId=" + sessionId +
                ", isUser=" + isUser +
                '}';
    }
}
