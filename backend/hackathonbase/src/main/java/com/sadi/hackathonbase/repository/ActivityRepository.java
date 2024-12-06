package com.sadi.hackathonbase.repository;

import com.sadi.hackathonbase.models.Activity;
import com.sadi.hackathonbase.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUser(User user, Sort sort);
}