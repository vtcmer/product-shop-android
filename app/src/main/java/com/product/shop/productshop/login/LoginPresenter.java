package com.product.shop.productshop.login;

import com.product.shop.productshop.login.events.LoginEvent;
import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 10/07/2016.
 */
public interface LoginPresenter {

    void onCreate();
    void onDestroy();

    void checkUserAuth(final User user);
    void login(final String email, final String password);
    void registerUser(final String email, final String password);

    void onEventMainThread(final LoginEvent event);
}
