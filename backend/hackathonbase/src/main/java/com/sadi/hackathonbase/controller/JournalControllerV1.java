package com.sadi.hackathonbase.controller;

import com.sadi.hackathonbase.models.Journal;
import com.sadi.hackathonbase.models.requests.JournalReq;
import com.sadi.hackathonbase.service.JournalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/journals")
public class JournalControllerV1 {
    private final JournalService journalService;
    private final Logger log = LoggerFactory.getLogger(JournalControllerV1.class);

    public JournalControllerV1(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping
    public ResponseEntity<Void> createJournal(@Valid @RequestBody JournalReq req) {
        log.debug("Add journal req {}", req);
        Long id = journalService.createJournal(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        log.debug("Delete journal req for {}", id);
        journalService.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedModel<Map<String, Object>>> getJournals(
            @RequestParam(required = false, defaultValue = "1000-01-01") LocalDate startDate,
            @RequestParam(required = false, defaultValue = "9999-12-31") LocalDate endDate,
            @RequestParam(required = false, defaultValue = "0") Integer pageNo,
            @RequestParam(required = false, defaultValue = "30") Integer pageSize,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        log.debug("Get journal req for");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection, "createdAt");
        Page<Journal> journalPage = journalService.getAllJournals(startDate, endDate, pageable);
        List<Journal> journals = journalPage.getContent();
        List<Map<String, Object>> journalMapList = new ArrayList<>();
        for (Journal j : journals){
            Map<String, Object> journalMap = new HashMap<>();
            journalMap.put("id", j.getId());
            journalMap.put("title", j.getTitle());
//            journalMap.put("content", j.getContent());
            journalMap.put("createdAt", j.getCreatedAt());
            journalMapList.add(journalMap);
        }
        Page<Map<String, Object>> journalMapPage = new PageImpl<>(journalMapList, pageable, journalPage.getTotalElements());
        return ResponseEntity.ok(new PagedModel<>(journalMapPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getJournal(@PathVariable Long id) {
        log.debug("Get journal req for {}", id);
        Journal journal = journalService.getJournal(id);
        journalService.ValidateJournal(journal);
        Map<String, Object> response = new HashMap<>();
        response.put("title", journal.getTitle());
        response.put("content", journal.getContent());
        response.put("createdAt", journal.getCreatedAt());
        return ResponseEntity.ok(response);
    }
}
