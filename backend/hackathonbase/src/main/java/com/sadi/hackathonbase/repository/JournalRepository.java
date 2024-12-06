package com.sadi.hackathonbase.repository;

import com.sadi.hackathonbase.models.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    @Query("select j from Journal j where j.createdAt between :localDateTime and :localDateTime1 and j.user.id = :ownerID")
    Page<Journal> findAllByCreatedAtBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1, Long ownerID, Pageable pageable);
}
