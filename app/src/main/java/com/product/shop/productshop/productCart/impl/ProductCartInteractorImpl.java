package com.product.shop.productshop.productCart.impl;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productCart.ProductCartInteractor;
import com.product.shop.productshop.productCart.ProductCartRepository;

/**
 * Created by vtcmer on 16/07/2016.
 */
public class ProductCartInteractorImpl implements ProductCartInteractor {

    private ProductCartRepository repository;

    public ProductCartInteractorImpl(ProductCartRepository repository) {
        this.repository = repository;
    }



    @Override
    public void getAllProducts(User user) {
        this.repository.getAllProducts(user);
    }

    @Override
    public void deleteProduct(User user, Product product) {
        this.repository.deleteProduct(user,product);
    }
}
