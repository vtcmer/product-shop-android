package com.product.shop.productshop;

import android.app.Application;

import com.firebase.client.Firebase;
import com.product.shop.productshop.api.firebase.di.FirebaseModule;
import com.product.shop.productshop.lib.di.LibsModule;
import com.product.shop.productshop.login.di.DaggerLoginComponent;
import com.product.shop.productshop.login.di.LoginComponent;
import com.product.shop.productshop.login.di.LoginModule;
import com.product.shop.productshop.login.ui.LoginView;
import com.product.shop.productshop.productCart.di.DaggerProductCartComponent;
import com.product.shop.productshop.productCart.di.ProductCartComponent;
import com.product.shop.productshop.productCart.di.ProductCartModule;
import com.product.shop.productshop.productCart.ui.ProductCartActivity;
import com.product.shop.productshop.productCart.ui.ProductCartView;
import com.product.shop.productshop.productCart.ui.adapters.OnItemClickListenerProductCart;
import com.product.shop.productshop.productList.di.DaggerProductListComponent;
import com.product.shop.productshop.productList.di.ProductListComponent;
import com.product.shop.productshop.productList.di.ProductListModule;
import com.product.shop.productshop.productList.ui.ProductListActivity;
import com.product.shop.productshop.productList.ui.ProductListView;
import com.product.shop.productshop.productList.ui.adapters.OnItemClickListenerProductList;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by vtcmer on 10/07/2016.
 */
public class ProductShopApp extends Application {

    private final static String FIREBASE_URL = "https://products-shop.firebaseio.com/";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";
    private final static String SHARED_PREFERENCES_FIELD_NAME_USER_ID = "UserId" ;
    private final static String SHARED_PREFERENCES_FIELD_NAME_EMAIL = "Email" ;

    private FirebaseModule firebaseModule;
    private ProductShopAppModule productShopAppModule;

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
                .productShopAppModule(this.productShopAppModule)
                .libsModule(new LibsModule())
                .firebaseModule(this.firebaseModule)
                .build();
    }

    public ProductListComponent getProductListComponent(ProductListView view, ProductListActivity activity, OnItemClickListenerProductList onItemClickListenerProductList){
        return DaggerProductListComponent
                .builder()
                .productShopAppModule(this.productShopAppModule)
                .productListModule(new ProductListModule(view,onItemClickListenerProductList))
                .libsModule(new LibsModule(activity))
                .build();
    }


   public ProductCartComponent getProductCartComponent(ProductCartView view, ProductCartActivity activity, OnItemClickListenerProductCart onItemClickListenerProductCart){
       return DaggerProductCartComponent
               .builder()
               .productShopAppModule(this.productShopAppModule)
               .productCartModule(new ProductCartModule(view, onItemClickListenerProductCart))
               .libsModule(new LibsModule(activity))
               .build();
   }


    private void initModules() {
        this.productShopAppModule = new ProductShopAppModule(this);
        this.firebaseModule = new FirebaseModule(FIREBASE_URL);
    }

    /**
     * Inicialización de Firebase
     */
    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }


    public String getSharedPreferentecesName(){
        return SHARED_PREFERENCES_NAME;
    }

    public String getSharedPreferentecesFieldUserId(){
        return SHARED_PREFERENCES_FIELD_NAME_USER_ID;
    }

    public String getSharedPreferentecesFieldEmail(){
        return SHARED_PREFERENCES_FIELD_NAME_EMAIL;
    }
}
