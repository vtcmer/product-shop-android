package com.product.shop.productshop.productCart.di;

import android.content.Context;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.productCart.ProductCartPresenter;
import com.product.shop.productshop.productCart.impl.ProductCartPresenterImpl;
import com.product.shop.productshop.productCart.ui.ProductCartView;
import com.product.shop.productshop.productCart.ui.adapters.OnItemClickListenerProductCart;
import com.product.shop.productshop.productCart.ui.adapters.ProductCartAdapter;
import com.product.shop.productshop.productList.ProductListInteractor;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 14/07/2016.
 */
@Module
public class ProductCartModule {

    private ProductCartView view;
    private OnItemClickListenerProductCart onItemClickListenerProductCart;

    public ProductCartModule(ProductCartView view, OnItemClickListenerProductCart onItemClickListenerProductCart) {
        this.view = view;
        this.onItemClickListenerProductCart = onItemClickListenerProductCart;
    }

    @Singleton
    @Provides
    ProductCartView providesProductCartView(){
        return this.view;
    }



    @Singleton
    @Provides
    OnItemClickListenerProductCart providesOnItemClickListenerProductCart(){
        return this.onItemClickListenerProductCart;
    }

    @Singleton
    @Provides
    ProductCartPresenter providesProductCartPresenter(EventBus eventBus, ProductCartView view, ProductListInteractor productListInteractor){
        return new ProductCartPresenterImpl(eventBus,view, productListInteractor);
    }

    @Singleton
    @Provides
    ProductCartAdapter providesProductCartAdapter(Context context, List<Product> products, ImageLoader imageLoader, OnItemClickListenerProductCart onItemClickListenerProductCart){
        return new ProductCartAdapter(context, products,imageLoader, onItemClickListenerProductCart);
    }


}
