package com.example1.dairyease.ModelResponse;

public class TokenOTPResponse {

    public Integer status;
    public String message;
    public String token;

    public TokenOTPResponse(Integer status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
