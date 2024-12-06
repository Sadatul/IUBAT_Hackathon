package com.sadi.hackathonbase.service;

import com.sadi.hackathonbase.exceptions.BadRequestFromUserException;
import com.sadi.hackathonbase.models.Activity;
import com.sadi.hackathonbase.models.SubActivity;
import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.requests.ActivityPostRequest;
import com.sadi.hackathonbase.models.requests.CompleteActivityReq;
import com.sadi.hackathonbase.repository.ActivityRepository;
import com.sadi.hackathonbase.repository.SubActivityRepository;
import com.sadi.hackathonbase.repository.UserRepository;
import com.sadi.hackathonbase.utils.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final SubActivityRepository subActivityRepository;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, SubActivityRepository subActivityRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.subActivityRepository = subActivityRepository;
    }


    public Long addActivity(ActivityPostRequest req) {
        User user = userRepository.findById(SecurityUtils.getOwnerID()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        Activity activity = activityRepository.save(new Activity(req.getTitle(), user, LocalDateTime.now().plusDays(1)));
        for (String s: req.getSubActivities())
        {
            subActivityRepository.save(new SubActivity(s, activity));
        }
        return activity.getId();
    }

    public List<Activity> getActivities() {
        return activityRepository.findByUser(new User(SecurityUtils.getOwnerID()), Sort.by(Sort.Direction.ASC, "deadline"));
    }

    public Integer completeActivity(@Valid CompleteActivityReq req) {
        Activity activity = activityRepository.findById(req.getActivityId()).orElseThrow(
                () -> new IllegalArgumentException("Activity not found")
        );
        if(!activity.getUser().getId().equals(SecurityUtils.getOwnerID())){
            throw new BadRequestFromUserException("You are not allowed to complete this activity");
        }
        SubActivity subActivity = subActivityRepository.findById(req.getSubActivityId()).orElseThrow(
                () -> new IllegalArgumentException("SubActivity not found")
        );
        if(subActivity.getCompleted()){
            throw new BadRequestFromUserException("Activity is already completed");
        }
        if(!Objects.equals(subActivity.getActivity().getId(), activity.getId())){
            throw new BadRequestFromUserException("SubActivity does not belong to the activity");
        }
        Integer numberOfCompletedSubActivities = activity.getRemainingSubActivities();
        subActivity.setCompleted(true);
        subActivityRepository.save(subActivity);
        return numberOfCompletedSubActivities - 1;
    }
}
