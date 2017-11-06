package com.projeto.clubedowhisky.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fábio Lucena Riba on 03/11/2017.
 */

public class Drinks implements Parcelable {

    private Integer id = 0;
    private String name = "";
    private Integer quantities = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.quantities);
        dest.writeValue(this.id);
    }

    public Drinks() {
    }

    protected Drinks(Parcel in) {
        this.name = in.readString();
        this.quantities = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Drinks> CREATOR = new Creator<Drinks>() {
        @Override
        public Drinks createFromParcel(Parcel source) {
            return new Drinks(source);
        }

        @Override
        public Drinks[] newArray(int size) {
            return new Drinks[size];
        }
    };
}
