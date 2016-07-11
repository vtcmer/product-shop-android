package com.product.shop.productshop.login.impl;

import com.firebase.client.FirebaseError;
import com.product.shop.productshop.api.firebase.FirebaseApi;
import com.product.shop.productshop.api.firebase.callback.FirebaseActionListenerCallback;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.login.LoginRepository;
import com.product.shop.productshop.login.events.LoginEvent;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private EventBus eventBus;
    private FirebaseApi firebaseApi;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseApi firebaseApi) {
        this.eventBus = eventBus;
        this.firebaseApi = firebaseApi;
    }

    @Override
    public void login(String email, String password) {

        this.firebaseApi.signIn(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(LoginEvent.LOGIN_SUCCESS);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.LOGIN_FAIL, error.getMessage());
            }
        });

    }

    @Override
    public void register(String email, String password) {

        this.firebaseApi.signUp(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(LoginEvent.REGISTER_SUCCESS);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.REGISTER_FAIL, error.getMessage());
            }
        });

    }

    private void post(final int type){
        this.post(type,null);
    }

    private void post(final int type, final String msg){
        LoginEvent event = new LoginEvent();
        event.setType(type);
        event.setMsg(msg);

        this.eventBus.post(event);


    }
}
