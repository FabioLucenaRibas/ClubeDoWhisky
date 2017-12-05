package com.projeto.clubedowhisky.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Renato on 20/11/2017.
 */

public class Users implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAt;

    private String email;
    private String username;

    @SerializedName("is_enabled")
    private Boolean isEnabled;
    private String name;

    private String password;
    @SerializedName("updated_at")
    private String updatedAt;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
