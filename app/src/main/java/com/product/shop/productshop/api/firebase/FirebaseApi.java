package com.product.shop.productshop.api.firebase;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.product.shop.productshop.api.firebase.callback.FirebaseActionListenerCallback;
import com.product.shop.productshop.model.User;

import java.util.Map;

/**
 * Created by vtcmer on 10/07/2016.
 */
public class FirebaseApi {

    private Firebase firebase;

    public FirebaseApi(Firebase firebase) {
        this.firebase = firebase;
    }


    /**
     * Autenticación del usuario en la aplicación
     * @param email
     * @param password
     * @param listener
     */
    public void signIn(final String email, final String password, final FirebaseActionListenerCallback listener) {

        this.firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listener.onSuccess();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });

    }

        /**
         * Registro de un nuevo usuario en la aplicación
         * @param email
         * @param password
         * @param listener
         */
    public void signUp(final String email, final String password, final FirebaseActionListenerCallback listener){

        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                listener.onSuccess();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listener.onError(firebaseError);
            }
        });

    }

    /**
     * Recuperación del usuario autenticado
     * @return
     */
    public User getAuthUser(){

        User user = null;
        if (this.firebase.getAuth()!= null){
            Map<String, Object> providerData = this.firebase.getAuth().getProviderData();
            user = new User();
            user.setEmail(providerData.get("email").toString());
            user.setUserId(this.firebase.getAuth().getUid());
        }

        return user;
    }

    /**
     * Logout de la aplicación
     */
    public void logout(){
        this.firebase.unauth();
    }



}
