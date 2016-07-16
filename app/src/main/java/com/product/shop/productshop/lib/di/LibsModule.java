package com.product.shop.productshop.lib.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.lib.impl.GlideImageLoader;
import com.product.shop.productshop.lib.impl.GreenRobotEventBus;
import com.product.shop.productshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 11/07/2016.
 */
@Module
public class LibsModule {

    private Activity activity;

    public LibsModule() {
    }

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Singleton
    @Provides
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return  org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Singleton
    @Provides
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }


    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }


    @Singleton
    @Provides
    List<Product> providesProductList(){
        return new ArrayList<Product>();
    }

}
