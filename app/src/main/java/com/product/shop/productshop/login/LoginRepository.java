package com.product.shop.productshop.login;

/**
 * Created by vtcmer on 11/07/2016.
 */
public interface LoginRepository {

    void login(final String email, final String password);
    void register(final String email, final String password);
}
