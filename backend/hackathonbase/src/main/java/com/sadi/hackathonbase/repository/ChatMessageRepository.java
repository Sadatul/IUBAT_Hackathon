package com.sadi.hackathonbase.repository;

import com.sadi.hackathonbase.models.ChatMessage;
import com.sadi.hackathonbase.models.responses.ChatMessageRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("select new com.sadi.hackathonbase.models.responses.ChatMessageRes(cm.message, cm.isUser, cm.sentAt) from ChatMessage cm where cm.session.id = :sessionId order by cm.sentAt")
    List<ChatMessageRes> findBySessionId(Long sessionId);
}
