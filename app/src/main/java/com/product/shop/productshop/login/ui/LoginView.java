package com.product.shop.productshop.login.ui;

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


    /**
     * Mensaje confirmación
     */
    void onLoginSuccess();
    void onLoginError(String error);
    void onRegisterSuccess();
    void onRegisterError(String error);

    /**
     * Redirección al listado de productos
     */
    void redirectToProductListView();
}
