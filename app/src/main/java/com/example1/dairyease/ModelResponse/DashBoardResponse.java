package com.example1.dairyease.ModelResponse;

public class DashBoardResponse {

    private String name;
    private String profile_photo;
    private String total_balance;
    private String total_milk;
    private String per_liter_amt;

    public DashBoardResponse(String name, String profile_photo, String total_balance, String total_milk, String per_liter_amt) {
        this.name = name;
        this.profile_photo = profile_photo;
        this.total_balance = total_balance;
        this.total_milk = total_milk;
        this.per_liter_amt = per_liter_amt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = total_balance;
    }

    public String getTotal_milk() {
        return total_milk;
    }

    public void setTotal_milk(String total_milk) {
        this.total_milk = total_milk;
    }

    public String getPer_liter_amt() {
        return per_liter_amt;
    }

    public void setPer_liter_amt(String per_liter_amt) {
        this.per_liter_amt = per_liter_amt;
    }
}