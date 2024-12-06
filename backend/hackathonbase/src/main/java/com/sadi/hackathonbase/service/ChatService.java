package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.exceptions.BadRequestFromUserException;
import com.sadi.hackathonbase.exceptions.NotFoundExceptionHack;
import com.sadi.hackathonbase.models.ChatMessage;
import com.sadi.hackathonbase.models.ChatSession;
import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.requests.ChatMessageRequest;
import com.sadi.hackathonbase.models.requests.ChatSessionRequest;
import com.sadi.hackathonbase.models.responses.ChatMessageRes;
import com.sadi.hackathonbase.models.responses.ChatSessionRes;
import com.sadi.hackathonbase.repository.ChatMessageRepository;
import com.sadi.hackathonbase.repository.ChatSessionRepository;
import com.sadi.hackathonbase.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    private final ChatSessionRepository chatSessionRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatSession getChatSession(Long id) {
        return chatSessionRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHack("Chat session not found"));
    }

    public ChatService(ChatSessionRepository chatSessionRepository, ChatMessageRepository chatMessageRepository) {
        this.chatSessionRepository = chatSessionRepository;
        this.chatMessageRepository = chatMessageRepository;
    }


    public ChatSession createChatSession(ChatSessionRequest chatSessionRequest) {
        Long userId = SecurityUtils.getOwnerID();
        ChatSession chatSession = new ChatSession(new User(userId), chatSessionRequest.name());
        return chatSessionRepository.save(chatSession);
    }

    public List<ChatSessionRes> getChatSession() {
        Long userId = SecurityUtils.getOwnerID();
        return chatSessionRepository.findByUserIdSorted(userId);
    }

    public Long createChatMessage(ChatMessageRequest messageRequest) {
        ChatSession chatSession = getChatSession(messageRequest.getSessionId());
        ChatMessage chatMessage = new ChatMessage(chatSession, messageRequest.getMessage(), messageRequest.getIsUser(), LocalDateTime.now());
        return chatMessageRepository.save(chatMessage).getId();
    }

    public List<ChatMessageRes> getChatMessages(Long id) {
        ChatSession chatSession = getChatSession(id);
        validateChatSession(chatSession);
        return chatMessageRepository.findBySessionId(id);
    }

    private void validateChatSession(ChatSession chatSession) {
        if(!chatSession.getUser().getId().equals(SecurityUtils.getOwnerID())) {
            throw new BadRequestFromUserException("User doesn't have access to this chat session");
        }
    }

    public void deleteSession(Long id) {
        ChatSession chatSession = getChatSession(id);
        validateChatSession(chatSession);
        chatSessionRepository.delete(chatSession);
    }
}
