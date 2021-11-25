package com.shop.shop.models;

import java.util.Date;

public class Product {
    private long id;
    private String name;
    private String rating;
    private Date createdAt;
    private long categoryId;
    private Float price;
    private String type;

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

  /*  @GeneratedValue(strategy=GenerationType.AUTO) */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
