package com.product.shop.productshop.productList.events;

import com.product.shop.productshop.model.Product;

import java.util.List;

/**
 * Created by vtcmer on 12/07/2016.
 */
public class ProductListEvent {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int type;
    private String  msg;
    private List<Product> products;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
