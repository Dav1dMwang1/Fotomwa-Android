package com.example.dijonkariz.fotomwa.model;

import java.sql.Timestamp;

public class Message {
    private Long id;
    private String title, description;
    private Timestamp notification_time;

    public Message() {}

    public Message(String title, String description, Timestamp notification_time) {
        this.title = title;
        this.description = description;
        this.notification_time = notification_time;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(Timestamp notification_time) {
        this.notification_time = notification_time;
    }
}
