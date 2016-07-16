package com.product.shop.productshop.login.impl;

import com.firebase.client.FirebaseError;
import com.product.shop.productshop.api.firebase.FirebaseApi;
import com.product.shop.productshop.api.firebase.callback.FirebaseActionListenerCallback;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.login.LoginRepository;
import com.product.shop.productshop.login.events.LoginEvent;
import com.product.shop.productshop.model.User;

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
                User user = firebaseApi.getAuthUser();
                post(LoginEvent.LOGIN_SUCCESS, user);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.LOGIN_FAIL, error.getMessage());
            }
        });

    }

    @Override
    public void register(final String email, final String password) {

        this.firebaseApi.signUp(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                //post(LoginEvent.REGISTER_SUCCESS);
                login(email,password);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.REGISTER_FAIL, error.getMessage());
            }
        });

    }


    private void post(final int type){
        this.post(type,null,null);
    }

    private void post(final int type, final User user){
        this.post(type,null,user);
    }

    private void post(final int type, final String msg){
        this.post(type,msg,null);
    }

    private void post(final int type, final String msg, final User user){
        LoginEvent event = new LoginEvent();
        event.setType(type);
        event.setMsg(msg);
        event.setUser(user);

        this.eventBus.post(event);


    }
}
