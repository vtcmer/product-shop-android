package com.product.shop.productshop.product.di;

import com.product.shop.productshop.ProductShopAppModule;
import com.product.shop.productshop.lib.di.LibsModule;
import com.product.shop.productshop.productCart.di.ProductCartModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vtcmer on 14/07/2016.
 */
@Singleton
@Component(modules ={ProductModule.class, LibsModule.class})
public interface ProductComponent {
}
