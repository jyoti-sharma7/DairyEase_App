package com.example1.dairyease.ModelResponse;

public class UpdateProfileResponse {

    private String message;

    public UpdateProfileResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
