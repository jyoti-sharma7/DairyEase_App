package com.example1.dairyease.ModelResponse;

public class VerifyOtpResponse {

    String message;

    public VerifyOtpResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
