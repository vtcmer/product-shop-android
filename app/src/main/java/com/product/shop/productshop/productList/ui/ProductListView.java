package com.product.shop.productshop.productList.ui;

import com.product.shop.productshop.model.Product;

import java.util.List;

/**
 * Created by vtcmer on 12/07/2016.
 */
public interface ProductListView {

    void showProgressBar();
    void hideProgressBar();


    void setProducts(final List<Product> products);

}
