package com.product.shop.productshop.lib.di;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.lib.impl.GreenRobotEventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 11/07/2016.
 */
@Module
public class LibsModule {

    public LibsModule() {
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

}
