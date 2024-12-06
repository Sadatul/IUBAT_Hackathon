package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.NotNull;

public class CompleteActivityReq {
    @NotNull
    private Long activityId;
    @NotNull
    private Long subActivityId;

    public CompleteActivityReq(Long activityId, Long subActivityId) {
        this.activityId = activityId;
        this.subActivityId = subActivityId;
    }

    public CompleteActivityReq() {
    }

    public @NotNull Long getActivityId() {
        return activityId;
    }

    public void setActivityId(@NotNull Long activityId) {
        this.activityId = activityId;
    }

    public @NotNull Long getSubActivityId() {
        return subActivityId;
    }

    public void setSubActivityId(@NotNull Long subActivityId) {
        this.subActivityId = subActivityId;
    }

    @Override
    public String toString() {
        return "CompleteActivityReq{" +
                "activityId=" + activityId +
                ", subActivityId=" + subActivityId +
                '}';
    }
}
