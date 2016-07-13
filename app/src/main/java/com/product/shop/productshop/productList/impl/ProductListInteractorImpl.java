package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.productList.ProductListInteractor;
import com.product.shop.productshop.productList.ProductListRepository;

/**
 * Created by vtcmer on 12/07/2016.
 */
public class ProductListInteractorImpl implements ProductListInteractor {

    private ProductListRepository repository;

    public ProductListInteractorImpl(ProductListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadProductList() {
        this.repository.loadProductList();
    }
}
