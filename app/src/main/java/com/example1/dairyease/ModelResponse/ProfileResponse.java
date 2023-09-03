package com.example1.dairyease.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    public Integer status;
    public String message;

    @SerializedName("data")
    @Expose
    public ProfileData profiledata;

    public ProfileResponse(Integer status, String message, ProfileData profiledata) {
        this.status = status;
        this.message = message;
        this.profiledata = profiledata;
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

    public ProfileData getProfiledata() {
        return profiledata;
    }

    public void setProfiledata(ProfileData profiledata) {
        this.profiledata = profiledata;
    }
}
