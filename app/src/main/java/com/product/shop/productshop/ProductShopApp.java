package com.product.shop.productshop;

import android.app.Application;

import com.firebase.client.Firebase;
import com.product.shop.productshop.api.firebase.di.FirebaseModule;
import com.product.shop.productshop.lib.di.LibsModule;
import com.product.shop.productshop.login.di.DaggerLoginComponent;
import com.product.shop.productshop.login.di.LoginComponent;
import com.product.shop.productshop.login.di.LoginModule;
import com.product.shop.productshop.login.ui.LoginView;
import com.product.shop.productshop.productList.di.DaggerProductListComponent;
import com.product.shop.productshop.productList.di.ProductListComponent;
import com.product.shop.productshop.productList.di.ProductListModule;
import com.product.shop.productshop.productList.ui.ProductListActivity;
import com.product.shop.productshop.productList.ui.ProductListView;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by vtcmer on 10/07/2016.
 */
public class ProductShopApp extends Application {

    private final static String FIREBASE_URL = "https://products-shop.firebaseio.com/";

    private FirebaseModule firebaseModule;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initFirebase();
        this.initDBFlow();
        this.initModules();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.DBTearDown();
    }

    /**
     * Inicialización de DBFlow
     */
    private void initDBFlow() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    public LoginComponent getLoginComponent(final LoginView view){

        return DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(view))
                .libsModule(new LibsModule())
                .firebaseModule(this.firebaseModule)
                .build();
    }

    public ProductListComponent getProductListComponent(ProductListView view, ProductListActivity activity){
        return DaggerProductListComponent
                .builder()
                .productListModule(new ProductListModule(view))
                .libsModule(new LibsModule(activity))
                .build();
    }


    private void initModules() {
        this.firebaseModule = new FirebaseModule(FIREBASE_URL);
    }

    /**
     * Inicialización de Firebase
     */
    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }
}
