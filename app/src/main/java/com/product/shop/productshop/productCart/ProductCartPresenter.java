package com.product.shop.productshop.productCart;


import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productCart.events.ProductCartEvent;


/**
 * Created by vtcmer on 14/07/2016.
 */
public interface ProductCartPresenter {

    void onCreate();
    void onDestroy();

    void getAllProducts(final User user);
    void deleteProduct(final User user, final Product product);

    void onEventMainThread(final ProductCartEvent event);

}
