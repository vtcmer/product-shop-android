package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.api.firebase.FirebaseApi;
import com.product.shop.productshop.productList.SessionRepository;

/**
 * Created by vtcmer on 16/07/2016.
 */
public class SessionRepositoryImpl implements SessionRepository {

    private FirebaseApi firebaseApi;

    public SessionRepositoryImpl(FirebaseApi firebaseApi) {
        this.firebaseApi = firebaseApi;
    }


    @Override
    public void logout() {
        this.firebaseApi.logout();
    }
}
