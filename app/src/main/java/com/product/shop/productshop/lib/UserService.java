package com.product.shop.productshop.lib;

import android.content.SharedPreferences;

import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 14/07/2016.
 */
public class UserService {

    private SharedPreferences sharedPreferences;
    private ProductShopApp app;

    public UserService(SharedPreferences sharedPreferences, ProductShopApp app) {
        this.sharedPreferences = sharedPreferences;
        this.app = app;
    }

    public User getUserAuth(){
        User user = new User();
        user.setUserId(this.sharedPreferences.getString(app.getSharedPreferentecesFieldUserId(),null));
        user.setEmail(this.sharedPreferences.getString(app.getSharedPreferentecesFieldEmail(),null));
        return user;
    }

    public void setUserAuth(final User user){

        SharedPreferences.Editor edit=  this.sharedPreferences.edit();
        edit.putString(app.getSharedPreferentecesFieldUserId(),user.getUserId());
        edit.putString(app.getSharedPreferentecesFieldEmail(),user.getEmail());
        edit.apply();

    }


}
