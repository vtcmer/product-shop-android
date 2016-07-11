package com.product.shop.productshop.login;

import com.product.shop.productshop.login.events.LoginEvent;

/**
 * Created by vtcmer on 10/07/2016.
 */
public interface LoginPresenter {

    void onCreate();
    void onDestroy();

    void login(final String email, final String password);
    void registerUser(final String email, final String password);

    void onEventMainThread(final LoginEvent event);
}
