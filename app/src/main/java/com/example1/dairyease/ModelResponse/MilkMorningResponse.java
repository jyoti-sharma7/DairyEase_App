package com.example1.dairyease.ModelResponse;

import java.util.List;

public class MilkMorningResponse {

    private Integer status;
    private String message;
    private List<MilkMorningList> data;

    public MilkMorningResponse(Integer status, String message, List<MilkMorningList> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<MilkMorningList> getData() {
        return data;
    }

    public void setData(List<MilkMorningList> data) {
        this.data = data;
    }
}
