package com.example1.dairyease.ModelResponse;

import java.util.List;

public class MilkResponse {

    public Integer status;
    public String message;
    public List<Data> data;

    public MilkResponse(Integer status, String message, List<Data> data) {
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {

        public Integer id;
        public Integer userId;
        public String shift;
        public String date;
        public String perFatAmt;
        public String fatRate;
        public String perSnfAmt;
        public String snfRate;
        public String liter;
        public Integer totalFat;
        public Integer totalSnf;
        public Integer perLiterAmt;
        public Integer balance;
        public String createdAt;
        public String updatedAt;

        public Data(Integer id, Integer userId,
                    String shift, String date,
                    String perFatAmt, String fatRate,
                    String perSnfAmt, String snfRate,
                    String liter, Integer totalFat,
                    Integer totalSnf, Integer perLiterAmt,
                    Integer balance, String createdAt,
                    String updatedAt) {
            this.id = id;
            this.userId = userId;
            this.shift = shift;
            this.date = date;
            this.perFatAmt = perFatAmt;
            this.fatRate = fatRate;
            this.perSnfAmt = perSnfAmt;
            this.snfRate = snfRate;
            this.liter = liter;
            this.totalFat = totalFat;
            this.totalSnf = totalSnf;
            this.perLiterAmt = perLiterAmt;
            this.balance = balance;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPerFatAmt() {
            return perFatAmt;
        }

        public void setPerFatAmt(String perFatAmt) {
            this.perFatAmt = perFatAmt;
        }

        public String getFatRate() {
            return fatRate;
        }

        public void setFatRate(String fatRate) {
            this.fatRate = fatRate;
        }

        public String getPerSnfAmt() {
            return perSnfAmt;
        }

        public void setPerSnfAmt(String perSnfAmt) {
            this.perSnfAmt = perSnfAmt;
        }

        public String getSnfRate() {
            return snfRate;
        }

        public void setSnfRate(String snfRate) {
            this.snfRate = snfRate;
        }

        public String getLiter() {
            return liter;
        }

        public void setLiter(String liter) {
            this.liter = liter;
        }

        public Integer getTotalFat() {
            return totalFat;
        }

        public void setTotalFat(Integer totalFat) {
            this.totalFat = totalFat;
        }

        public Integer getTotalSnf() {
            return totalSnf;
        }

        public void setTotalSnf(Integer totalSnf) {
            this.totalSnf = totalSnf;
        }

        public Integer getPerLiterAmt() {
            return perLiterAmt;
        }

        public void setPerLiterAmt(Integer perLiterAmt) {
            this.perLiterAmt = perLiterAmt;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}


