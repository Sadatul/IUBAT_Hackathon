package com.sadi.hackathonbase.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ChatSession session;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false, name = "is_user")
    private Boolean isUser;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    public ChatMessage() {
    }

    public ChatMessage(ChatSession session, String message, Boolean isUser, LocalDateTime sentAt) {
        this.session = session;
        this.message = message;
        this.isUser = isUser;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatSession getSession() {
        return session;
    }

    public void setSession(ChatSession session) {
        this.session = session;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", session=" + session +
                ", message='" + message + '\'' +
                ", isUser=" + isUser +
                ", sentAt=" + sentAt +
                '}';
    }
}
