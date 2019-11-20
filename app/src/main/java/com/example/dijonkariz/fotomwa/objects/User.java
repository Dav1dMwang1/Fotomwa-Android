package com.example.dijonkariz.fotomwa.objects;

public class User {
    private String user_name;
    private String user_role;
    private String user_email;
    private int thumbnail;

    private User() {}

    public User(String user_name, String user_role, String user_email, int thumbnail) {
        this.user_name = user_name;
        this.user_role = user_role;
        this.user_email = user_email;
        this.thumbnail = thumbnail;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
