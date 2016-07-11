package com.product.shop.productshop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class Product {

    @SerializedName("recipe_id")
    private String productId;
    private String title;
    private double price;
    @SerializedName("image_url")
    private String imageUrl;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
