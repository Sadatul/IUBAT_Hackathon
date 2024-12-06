package com.sadi.hackathonbase.repository;

import com.sadi.hackathonbase.models.SubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, Long> {
}
