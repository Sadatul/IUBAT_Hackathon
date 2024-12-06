package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.exceptions.BadRequestFromUserException;
import com.sadi.hackathonbase.exceptions.NotFoundExceptionHack;
import com.sadi.hackathonbase.models.Journal;
import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.requests.JournalReq;
import com.sadi.hackathonbase.repository.JournalRepository;
import com.sadi.hackathonbase.repository.UserRepository;
import com.sadi.hackathonbase.utils.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class JournalService {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;

    public Journal getJournal(Long id){
        return journalRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHack("Journal not found"));
    }

    public void ValidateJournal(Journal journal){
        if(!SecurityUtils.getOwnerID().equals(journal.getUser().getId())){
            throw new BadRequestFromUserException("You are not the owner of this journal");
        }
    }
    public JournalService(JournalRepository journalRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    public Long createJournal(@Valid JournalReq req) {
        Long userId = SecurityUtils.getOwnerID();
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Journal journal = new Journal(user, req.getTitle(), req.getContent(), LocalDateTime.now());
        return journalRepository.save(journal).getId();
    }

    public void deleteJournal(Long id) {
        Journal journal = getJournal(id);
        ValidateJournal(journal);
        journalRepository.delete(journal);
    }

    public Page<Journal> getAllJournals(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return journalRepository.findAllByCreatedAtBetween(startDate.atStartOfDay(), endDate.atStartOfDay(), SecurityUtils.getOwnerID(), pageable);
    }
}
