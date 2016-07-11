package com.product.shop.productshop.api.firebase.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vtcmer on 10/07/2016.
 */
@Singleton
@Component(modules = {FirebaseModule.class})
public interface FirebaseComponent {
}
