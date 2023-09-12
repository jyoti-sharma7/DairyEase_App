package com.example1.dairyease.ModelResponse;

public class EventData {
    private Integer id;
    private String event_photo;
    private String event_date;
    private String event_venue;
    private String event_title;
    private String event_description;
    private String created_at;
    private String updated_at;
    private String event_image_url;

    public EventData(Integer id, String event_photo, String event_date, String event_venue, String event_title, String event_description, String created_at, String updated_at, String event_image_url) {
        this.id = id;
        this.event_photo = event_photo;
        this.event_date = event_date;
        this.event_venue = event_venue;
        this.event_title = event_title;
        this.event_description = event_description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.event_image_url = event_image_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
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

    public String getEvent_image_url() {
        return event_image_url;
    }

    public void setEvent_image_url(String event_image_url) {
        this.event_image_url = event_image_url;
    }
}
