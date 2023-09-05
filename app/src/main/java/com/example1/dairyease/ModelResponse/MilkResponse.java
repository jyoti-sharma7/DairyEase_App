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
        public Integer user_id;
        public String shift;
        public String date;
        public String per_fat_amt;
        public String fat_rate;
        public String per_snf_amt;
        public String snf_rate;
        public String liter;
        public Float  total_fat;
        public Float total_snf;
        public Float per_liter_amt;
        public Integer balance;
        public String created_at;
        public String updated_at;


        public Data(Integer id, Integer user_id, String shift, String date, String per_fat_amt, String fat_rate, String per_snf_amt, String snf_rate, String liter, Float total_fat, Float total_snf, Float per_liter_amt, Integer balance, String created_at, String updated_at) {
            this.id = id;
            this.user_id = user_id;
            this.shift = shift;
            this.date = date;
            this.per_fat_amt = per_fat_amt;
            this.fat_rate = fat_rate;
            this.per_snf_amt = per_snf_amt;
            this.snf_rate = snf_rate;
            this.liter = liter;
            this.total_fat = total_fat;
            this.total_snf = total_snf;
            this.per_liter_amt = per_liter_amt;
            this.balance = balance;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
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

        public String getPer_fat_amt() {
            return per_fat_amt;
        }

        public void setPer_fat_amt(String per_fat_amt) {
            this.per_fat_amt = per_fat_amt;
        }

        public String getFat_rate() {
            return fat_rate;
        }

        public void setFat_rate(String fat_rate) {
            this.fat_rate = fat_rate;
        }

        public String getPer_snf_amt() {
            return per_snf_amt;
        }

        public void setPer_snf_amt(String per_snf_amt) {
            this.per_snf_amt = per_snf_amt;
        }

        public String getSnf_rate() {
            return snf_rate;
        }

        public void setSnf_rate(String snf_rate) {
            this.snf_rate = snf_rate;
        }

        public String getLiter() {
            return liter;
        }

        public void setLiter(String liter) {
            this.liter = liter;
        }

        public Float getTotal_fat() {
            return total_fat;
        }

        public void setTotal_fat(Float total_fat) {
            this.total_fat = total_fat;
        }

        public Float getTotal_snf() {
            return total_snf;
        }

        public void setTotal_snf(Float total_snf) {
            this.total_snf = total_snf;
        }

        public Float getPer_liter_amt() {
            return per_liter_amt;
        }

        public void setPer_liter_amt(Float per_liter_amt) {
            this.per_liter_amt = per_liter_amt;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}

