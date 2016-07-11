package com.product.shop.productshop.login.impl;

import com.product.shop.productshop.login.LoginInteractor;
import com.product.shop.productshop.login.LoginRepository;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void login(String email, String password) {
        this.repository.login(email, password);
    }

    @Override
    public void register(String email, String password) {
        this.repository.register(email,password);
    }
}
