package com.product.shop.productshop.login.di;

import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.ProductShopAppModule;
import com.product.shop.productshop.api.firebase.di.FirebaseModule;
import com.product.shop.productshop.lib.di.LibsModule;
import com.product.shop.productshop.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vtcmer on 11/07/2016.
 */
@Singleton
@Component(modules = {LoginModule.class, LibsModule.class, FirebaseModule.class, ProductShopAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
