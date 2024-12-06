package com.sadi.hackathonbase.controller;

import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.requests.UserInfoUpdateRequest;
import com.sadi.hackathonbase.repository.UserRepository;
import com.sadi.hackathonbase.utils.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserInfoUpdateRequest request) {
        User user = userRepository.findById(SecurityUtils.getOwnerID()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if(request.getFullName() != null)
            user.setFullName(request.getFullName());
        if(request.getOccupation() != null)
            user.setOccupation(request.getOccupation());
        if(request.getHobbies() != null)
            user.setHobbies(request.getHobbies());
        user.setProfilePic(request.getProfilePic());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
