package com.sadi.hackathonbase.repository;

import com.sadi.hackathonbase.models.ChatSession;
import com.sadi.hackathonbase.models.responses.ChatSessionRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {

    @Query("select new com.sadi.hackathonbase.models.responses.ChatSessionRes(cs.id, cs.name) from ChatSession cs left join ChatMessage cm on cs.id = cm.session.id where cs.user.id = :userId group by cs.id, cs.name order by max(cm.sentAt) desc")
    List<ChatSessionRes> findByUserIdSorted(Long userId);
}
