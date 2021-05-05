package com.sunburt.sqlite.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String idProduct;
    private String name;
    private float price;
    private String status;

    public Product() {
    }

    public Product(String idProduct, String name, float price, String status) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
