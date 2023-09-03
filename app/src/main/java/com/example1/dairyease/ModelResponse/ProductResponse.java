package com.example1.dairyease.ModelResponse;

import java.util.List;

public class ProductResponse {

    public Boolean status;
    public String message;
    public List<ProductList> data;

    public ProductResponse(Boolean status, String message, List<ProductList> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductList> getData() {
        return data;
    }

    public void setData(List<ProductList> data) {
        this.data = data;
    }
}
