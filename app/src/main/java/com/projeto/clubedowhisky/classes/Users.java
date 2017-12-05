package com.projeto.clubedowhisky.classes;

import android.os.Parcel;
import android.os.Parcelable;

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


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
//        dest.writeString(this.createdAt);
//        dest.writeString(this.email);
//        dest.writeString(this.username);
//        dest.writeValue(this.isEnabled);
//        dest.writeString(this.name);
//        dest.writeString(this.password);
//        dest.writeString(this.updatedAt);
//    }
//
//    public Users() {
//    }
//
//    protected Users(Parcel in) {
//        this.id = in.readInt();
//        this.createdAt = in.readString();
//        this.email = in.readString();
//        this.username = in.readString();
//        this.isEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.name = in.readString();
//        this.password = in.readString();
//        this.updatedAt = in.readString();
//    }
//
//    public static final Creator<Users> CREATOR = new Creator<Users>() {
//        @Override
//        public Users createFromParcel(Parcel source) {
//            return new Users(source);
//        }
//
//        @Override
//        public Users[] newArray(int size) {
//            return new Users[size];
//        }
//    };
}
