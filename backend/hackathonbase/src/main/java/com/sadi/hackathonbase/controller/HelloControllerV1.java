package com.sadi.hackathonbase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello")
public class HelloControllerV1 {

    private final Logger log = LoggerFactory.getLogger(HelloControllerV1.class);

    @GetMapping("/healthy")
    public ResponseEntity<String> healthCheck(){
        log.info("Health check started");
        return ResponseEntity.ok("Hello World prod3");
    }
}
