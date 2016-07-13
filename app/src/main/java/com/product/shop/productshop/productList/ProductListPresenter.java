package com.product.shop.productshop.productList;

import com.product.shop.productshop.productList.events.ProductListEvent;

/**
 * Created by vtcmer on 12/07/2016.
 */
public interface ProductListPresenter {

    void onCreate();
    void onDestroy();

    void loadProductList();

    void onEventMainThread(final ProductListEvent event);
}
