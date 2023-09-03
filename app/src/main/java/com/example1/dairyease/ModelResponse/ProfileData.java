package com.example1.dairyease.ModelResponse;

public class ProfileData {

    public Integer id;
    public String name;
    public String email;
    public String contact;
    public String email_verified_at;
    public String created_at;
    public String updated_at;
    public String address;
    public String profile_photo;
    public Object otp_code;
    public Integer is_verified;
    public String profile_image_url;


    public ProfileData(Integer id, String name, String email, String contact, String email_verified_at, String created_at, String updated_at, String address, String profile_photo, Object otp_code, Integer is_verified, String profile_image_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.address = address;
        this.profile_photo = profile_photo;
        this.otp_code = otp_code;
        this.is_verified = is_verified;
        this.profile_image_url = profile_image_url;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public Object getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(Object otp_code) {
        this.otp_code = otp_code;
    }

    public Integer getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Integer is_verified) {
        this.is_verified = is_verified;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
}
