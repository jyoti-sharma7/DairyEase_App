package com.example1.dairyease.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    public Integer status;

    public String message;
    @SerializedName("access_token")
    @Expose
    public String accessToken;

    public LoginResponse(Integer status, String message, String accessToken) {
        this.status = status;
        this.message = message;
        this.accessToken = accessToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
