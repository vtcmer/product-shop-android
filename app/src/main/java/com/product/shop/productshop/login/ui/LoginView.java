package com.product.shop.productshop.login.ui;

import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 10/07/2016.
 */
public interface LoginView {

    /**
     * Muestra el progressbar
     */
    void showProgressBar();

    /**
     * Oculta el progressBar
     */
    void hideProgressBar();

    /**
     * Bloquea los componentes
     */
    void lockComponents();

    /**
     * Desbloqueda los componentes
     */
    void unlockComponents();


    void onLoginError(final String error);
    void onRegisterSuccess();
    void onRegisterError(final String error);
    void onLoginSuccess(final User user);

    /**
     * Redirección al listado de productos
     */
    void redirectToProductListView();
}
