package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
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

    @Override
    public void addProduct(final User user, final Product product) {
        this.repository.addProduct(user,product);
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
