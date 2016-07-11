package com.product.shop.productshop.api.firebase.di;

import com.firebase.client.Firebase;
import com.product.shop.productshop.api.firebase.FirebaseApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 10/07/2016.
 */
@Module
public class FirebaseModule {


    private String firebaseUrl;

    public FirebaseModule(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    /**
     * Recuperación del Api Wrapper para el acceso a firebase
     * @param firebase
     * @return
     */
    @Singleton
    @Provides
    FirebaseApi getProvidesFirebaseApi(final Firebase firebase){
        return new FirebaseApi(firebase);
    }

    /**
     * Recuperación de la instancia de Firebase
     * @return
     */
    @Singleton
    @Provides
    Firebase getProvidesFirebase(final String firebaseUrl){
        return new Firebase(firebaseUrl);
    }

    @Singleton
    @Provides
    String getProvidesFirebaseUrl(){
        return this.firebaseUrl;
    }
}
