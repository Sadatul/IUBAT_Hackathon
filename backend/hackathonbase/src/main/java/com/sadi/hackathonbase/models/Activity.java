package com.sadi.hackathonbase.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "activity")
    private List<SubActivity> subActivities;

    public Activity() {
    }

    public Activity(String title, User user, LocalDateTime deadline) {
        this.title = title;
        this.user = user;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<SubActivity> getSubActivities() {
        return subActivities;
    }

    public void setSubActivities(List<SubActivity> subActivities) {
        this.subActivities = subActivities;
    }

    public Integer getRemainingSubActivities()
    {
        Integer count = 0;
        for(SubActivity subActivity : subActivities)
        {
            if(!subActivity.getCompleted())
            {
                count++;
            }
        }
        return count;
    }

    public Boolean isCompleted()
    {
        return getRemainingSubActivities() == 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", subActivities=" + subActivities +
                '}';
    }
}
