package com.product.shop.productshop.productCart.ui;

import com.product.shop.productshop.model.Product;

import java.util.List;

/**
 * Created by vtcmer on 14/07/2016.
 */
public interface ProductCartView {

    void showProgressBar();
    void hideProgressBar();

    void setProducts(final List<Product> product);
    void onDeleteProductSuccess();


}
