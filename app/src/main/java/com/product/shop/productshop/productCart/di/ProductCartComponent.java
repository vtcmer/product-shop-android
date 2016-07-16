package com.product.shop.productshop.productCart.di;

import com.product.shop.productshop.ProductShopAppModule;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.lib.UserService;
import com.product.shop.productshop.lib.di.LibsModule;

import com.product.shop.productshop.productCart.ProductCartPresenter;
import com.product.shop.productshop.productCart.ui.adapters.ProductCartAdapter;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.di.ProductListModule;
import com.product.shop.productshop.productList.ui.adapters.ProductListAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vtcmer on 14/07/2016.
 */
@Singleton
@Component(modules ={ProductCartModule.class, LibsModule.class, ProductShopAppModule.class})
public interface ProductCartComponent {

    ProductCartPresenter getPresenter();
    ProductCartAdapter getAdapter();
    ImageLoader getImageLoader();
    UserService getUserService();

}
