package com.sadi.hackathonbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_activities")
public class SubActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    public SubActivity() {
    }

    public SubActivity(String title, Activity activity) {
        this.title = title;
        this.activity = activity;
        this.isCompleted = false;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "SubActivity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", activity=" + activity +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
