package com.projeto.clubedowhisky.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Drinks implements Parcelable {

    private Integer id = 0;
    private String name = "";
    private Integer quantities = 0;
    private String descricao = "";
    private Double preco = 0.0;
    private Double amountValue = 0.0;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
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
        dest.writeString(this.descricao);
        dest.writeValue(this.preco);
        dest.writeValue(this.amountValue);
    }

    public Drinks() {
    }

    protected Drinks(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.quantities = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descricao = in.readString();
        this.preco = (Double) in.readValue(Double.class.getClassLoader());
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