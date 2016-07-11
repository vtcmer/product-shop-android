package com.product.shop.productshop.login.ui;

/**
 * Created by vtcmer on 10/07/2016.
 */
public interface LoginView {

    void showProgressBar();
    void hideProgressBar();

    void lockComponents();
    void unlockComponents();


    void onLoginSuccess();
    void onLoginError(String error);
    void onRegisterSuccess();
    void onRegisterError(String error);
}
