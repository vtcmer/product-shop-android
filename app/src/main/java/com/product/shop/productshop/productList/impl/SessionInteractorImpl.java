package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.productList.SessionInteractor;
import com.product.shop.productshop.productList.SessionRepository;

/**
 * Created by vtcmer on 16/07/2016.
 */
public class SessionInteractorImpl implements SessionInteractor {

    private SessionRepository repository;

    public SessionInteractorImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        this.repository.logout();
    }
}
