package com.sadi.hackathonbase.controller;

import com.sadi.hackathonbase.models.Activity;
import com.sadi.hackathonbase.models.SubActivity;
import com.sadi.hackathonbase.models.requests.ActivityPostRequest;
import com.sadi.hackathonbase.models.requests.CompleteActivityReq;
import com.sadi.hackathonbase.service.ActivityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/activity")
public class ActivityControllerV1 {

    private final Logger log = LoggerFactory.getLogger(ActivityControllerV1.class);
    private final ActivityService activityService;

    public ActivityControllerV1(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<Void> addNewActivity(@Valid @RequestBody ActivityPostRequest req) {
        log.debug("Adding activity req {}", req);
        Long id = activityService.addActivity(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getActivities() {
        log.debug("Get activities request received");
        List<Activity> activities = activityService.getActivities();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Activity activity : activities) {
            if(activity.isCompleted()) continue;
            Map<String, Object> map = new HashMap<>();
            map.put("id", activity.getId());
            map.put("title", activity.getTitle());
            map.put("deadline", activity.getDeadline());
            List<Map<String, Object>> subActivities = new ArrayList<>();
            for(SubActivity subActivity : activity.getSubActivities()) {
                if(subActivity.getCompleted()) continue;
                Map<String, Object> subActivityMap = new HashMap<>();
                subActivityMap.put("id", subActivity.getId());
                subActivityMap.put("title", subActivity.getTitle());
                subActivityMap.put("isCompleted", subActivity.getCompleted());
                subActivities.add(subActivityMap);
            }
            map.put("subactivities", subActivities);
            map.put("isCompleted", activity.isCompleted());
            result.add(map);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> completeActivity(
            @Valid @RequestBody CompleteActivityReq req
    ) {
        log.debug("Complete Activity Request received: {}", req);
        Integer remaining = activityService.completeActivity(req);
        return ResponseEntity.ok(Collections.singletonMap("remaining", remaining));
    }
}
