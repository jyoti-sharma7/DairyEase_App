package com.example1.dairyease.ModelResponse;

public class ProductList {

    public Integer id;
    public String name;
    public Integer quantity;
    public String price;
    public String brand;
    public String description;
    public String product_photo;
    public String created_at;
    public String updated_at;
    public String product_image_url;


    public ProductList(Integer id, String name, Integer quantity, String price, String brand, String description, String product_photo, String created_at, String updated_at, String product_image_url) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.product_photo = product_photo;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.product_image_url = product_image_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
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

    public String getProduct_image_url() {
        return product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }
}
