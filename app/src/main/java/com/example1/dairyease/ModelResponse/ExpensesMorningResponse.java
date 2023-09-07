package com.example1.dairyease.ModelResponse;

import java.util.List;

public class ExpensesMorningResponse {

    private Integer status;
    private String messag;
    private List<ExpensesMorningList> data;

    public ExpensesMorningResponse(Integer status, String messag, List<ExpensesMorningList> data) {
        this.status = status;
        this.messag = messag;
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }

    public List<ExpensesMorningList> getData() {
        return data;
    }

    public void setData(List<ExpensesMorningList> data) {
        this.data = data;
    }
}
