package com.product.shop.productshop.api.producs;

import com.google.gson.annotations.SerializedName;
import com.product.shop.productshop.model.Product;

import java.util.List;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class ProductResults {

    private int count;

    @SerializedName("recipes")
    private List<Product> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
