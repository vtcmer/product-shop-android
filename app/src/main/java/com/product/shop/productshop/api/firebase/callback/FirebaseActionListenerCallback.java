package com.product.shop.productshop.api.firebase.callback;

import com.firebase.client.FirebaseError;

/**
 * Created by vtcmer on 10/07/2016.
 */
public interface FirebaseActionListenerCallback {

    /**
     * Callback a ejecutar si todo fue bien
     */
    void onSuccess();

    /**
     * Callback a ejecutar en caso de error
     * @param error
     */
    void onError(FirebaseError error);
}
