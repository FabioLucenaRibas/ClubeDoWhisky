package com.projeto.clubedowhisky.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Drinks implements Parcelable {

    private Integer id;
    private String name;
    private Integer quantities;
    private String description;
    private Double price;
    private Double amountValue;

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

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(Double amountValue) {
        this.amountValue = amountValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.quantities);
        dest.writeString(this.description);
        dest.writeValue(this.price);
        dest.writeValue(this.amountValue);
    }

    public Drinks() {
        super();
    }

    protected Drinks(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.quantities = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        this.amountValue = (Double) in.readValue(Double.class.getClassLoader());
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