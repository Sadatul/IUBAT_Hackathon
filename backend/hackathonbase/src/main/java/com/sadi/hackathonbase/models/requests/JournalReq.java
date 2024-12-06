package com.sadi.hackathonbase.models.requests;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JournalReq {
    @NotNull
    @Size(min = 1, max = 255, message = "Title length must be within 1 to 255 chars")
    private String title;

    @NotNull
    @Size(min = 1, max = 65535, message = "Content length must be within 1 to 65535 chars")
    private String content;

    public JournalReq() {
    }

    public JournalReq(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public @NotNull @Size(min = 1, max = 255, message = "Title length must be within 1 to 255 chars") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 1, max = 255, message = "Title length must be within 1 to 255 chars") String title) {
        this.title = title;
    }

    public @NotNull @Size(min = 1, max = 65535, message = "Content length must be within 1 to 65535 chars") String getContent() {
        return content;
    }

    public void setContent(@NotNull @Size(min = 1, max = 65535, message = "Content length must be within 1 to 65535 chars") String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JournalReq{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
