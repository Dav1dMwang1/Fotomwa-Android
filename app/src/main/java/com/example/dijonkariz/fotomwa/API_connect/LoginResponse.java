package com.example.dijonkariz.fotomwa.API_connect;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private int status;

    @SerializedName("msg")
    private String message;

    private String admin_id;
    private String admin_name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }
}
