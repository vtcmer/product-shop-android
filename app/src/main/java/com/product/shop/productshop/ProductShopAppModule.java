package com.product.shop.productshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.product.shop.productshop.lib.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 14/07/2016.
 */
@Module
public class ProductShopAppModule {

    private ProductShopApp app;

    public ProductShopAppModule(ProductShopApp app) {
        this.app = app;
    }


    @Singleton
    @Provides
    ProductShopApp providesProductShopApp(){
        return this.app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return this.app.getApplicationContext();
    }


    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(ProductShopApp app){
        return app.getSharedPreferences(this.app.getSharedPreferentecesName(),Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    UserService providesUserService(SharedPreferences sharedPreferences, ProductShopApp app){
        return new UserService(sharedPreferences,app);
    }


}
