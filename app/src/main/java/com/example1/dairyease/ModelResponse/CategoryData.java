package com.example1.dairyease.ModelResponse;

public class CategoryData {

    private Integer id;
    private String category_photo;
    private String category_name;
    private String created_at;
    private String updated_at;
    private String category_image_url;

    public CategoryData(Integer id, String category_photo, String category_name, String created_at, String updated_at, String category_image_url) {
        this.id = id;
        this.category_photo = category_photo;
        this.category_name = category_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category_image_url = category_image_url;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_photo() {
        return category_photo;
    }

    public void setCategory_photo(String category_photo) {
        this.category_photo = category_photo;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public String getCategory_image_url() {
        return category_image_url;
    }

    public void setCategory_image_url(String category_image_url) {
        this.category_image_url = category_image_url;
    }
}
