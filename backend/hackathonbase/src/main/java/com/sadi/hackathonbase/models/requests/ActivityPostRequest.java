package com.sadi.hackathonbase.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ActivityPostRequest {
    @NotNull
    private String title;

//    @Size(min = 1, max = 4)
    private List<String> subActivities;

    public ActivityPostRequest() {
    }

    public ActivityPostRequest(String title, List<String> subActivities) {
        this.title = title;
        this.subActivities = subActivities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubActivities() {
        return subActivities;
    }

    public void setSubActivities(List<String> subActivities) {
        this.subActivities = subActivities;
    }

    @Override
    public String toString() {
        return "ActivityPostRequest{" +
                "title='" + title + '\'' +
                ", subActivities=" + subActivities +
                '}';
    }
}
