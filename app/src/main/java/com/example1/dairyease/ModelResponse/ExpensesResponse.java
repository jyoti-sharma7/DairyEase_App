package com.example1.dairyease.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpensesResponse {

    public Integer status;
    public String message;
    @SerializedName("data")
    @Expose
    public List<ExpensesData> data;
    private String total_balance;

    public ExpensesResponse(Integer status, String message, List<ExpensesData> data, String total_balance) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<ExpensesData> getData() {
        return data;
    }

    public void setData(List<ExpensesData> data) {
        this.data = data;
    }

    public String getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(String total_balance) {
        this.total_balance = total_balance;
    }

    public static class ExpensesData {

        public Integer id;
        public Integer user_id;
        public String date;
        public String product;
        public String shift;
        public Integer quantity;
        public String unit;
        public String per_quantity;
        public Integer total_price;
        public String created_at;
        public String updated_at;

        public ExpensesData(Integer id, Integer user_id, String date, String product, String shift, Integer quantity, String unit, String per_quantity, Integer total_price, String created_at, String updated_at) {
            this.id = id;
            this.user_id = user_id;
            this.date = date;
            this.product = product;
            this.shift = shift;
            this.quantity = quantity;
            this.unit = unit;
            this.per_quantity = per_quantity;
            this.total_price = total_price;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPer_quantity() {
            return per_quantity;
        }

        public void setPer_quantity(String per_quantity) {
            this.per_quantity = per_quantity;
        }

        public Integer getTotal_price() {
            return total_price;
        }

        public void setTotal_price(Integer total_price) {
            this.total_price = total_price;
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
