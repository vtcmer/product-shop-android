package com.product.shop.productshop.productList.di;

import com.product.shop.productshop.ProductShopAppModule;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.lib.UserService;
import com.product.shop.productshop.lib.di.LibsModule;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.ui.adapters.ProductListAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vtcmer on 12/07/2016.
 */
@Singleton
@Component(modules ={ProductListModule.class, LibsModule.class, ProductShopAppModule.class})
public interface ProductListComponent {

    ProductListPresenter getProductListPresenter();
    ProductListAdapter getProductListAdapter();
    ImageLoader getImageLoader();
    UserService getUserService();
}
