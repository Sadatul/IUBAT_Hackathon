package com.sadi.hackathonbase.controller;

import com.sadi.hackathonbase.models.ChatSession;
import com.sadi.hackathonbase.models.requests.ChatMessageRequest;
import com.sadi.hackathonbase.models.requests.ChatSessionRequest;
import com.sadi.hackathonbase.models.responses.ChatMessageRes;
import com.sadi.hackathonbase.models.responses.ChatSessionRes;
import com.sadi.hackathonbase.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class ChatControllerV1 {
    private final Logger log = LoggerFactory.getLogger(ChatControllerV1.class);

    private final ChatService chatService;

    public ChatControllerV1(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> createChatSession(@RequestBody ChatSessionRequest chatSessionRequest) {
        log.debug("Chat session request received: {}", chatSessionRequest);
        ChatSession chatSession = chatService.createChatSession(chatSessionRequest);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//                .path("/{id}").buildAndExpand(chatSession.getId()).toUri();
        return ResponseEntity.ok(Collections.singletonMap("id", chatSession.getId()));
    }

    @PostMapping("/chat/messages")
    public ResponseEntity<Void> createChatMessage(@RequestBody ChatMessageRequest messageRequest) {
        log.debug("Chat message request received: {}", messageRequest);
        Long id = chatService.createChatMessage(messageRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/chat")
    public ResponseEntity<List<ChatSessionRes>> getChatSessions() {
        log.debug("Chat messages request received");
        List<ChatSessionRes> chatSessions = chatService.getChatSession();
        return ResponseEntity.ok(chatSessions);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<List<ChatMessageRes>> getChatMessages(@PathVariable Long id) {
        log.debug("Get Chat messages received for session {}", id);
        List<ChatMessageRes> chatMessages = chatService.getChatMessages(id);
        return ResponseEntity.ok(chatMessages);
    }

    @DeleteMapping("/chat/{id}")
    public ResponseEntity<Void> deleteChatSession(@PathVariable Long id) {
        log.debug("Delete request received for session {}", id);
        chatService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
