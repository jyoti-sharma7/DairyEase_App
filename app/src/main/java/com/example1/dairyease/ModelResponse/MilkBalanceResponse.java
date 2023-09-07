package com.example1.dairyease.ModelResponse;

public class MilkBalanceResponse {

    private Integer status;

    private String message;

    private String total_balance;


    public MilkBalanceResponse(Integer status, String message, String total_balance) {
        this.status = status;
        this.message = message;
        this.total_balance = total_balance;
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

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = total_balance;
    }
}
