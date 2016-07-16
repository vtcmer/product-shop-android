package com.product.shop.productshop.productCart.impl;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.login.events.LoginEvent;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productCart.ProductCartPresenter;
import com.product.shop.productshop.productCart.ui.ProductCartView;
import com.product.shop.productshop.productList.ProductListInteractor;
import com.product.shop.productshop.productList.events.ProductListEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by vtcmer on 14/07/2016.
 */
public class ProductCartPresenterImpl implements ProductCartPresenter {

    private EventBus eventBus;
    private ProductCartView view;
    private ProductListInteractor productListInteractor;

    public ProductCartPresenterImpl(EventBus eventBus, ProductCartView view, ProductListInteractor productListInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.productListInteractor = productListInteractor;
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.eventBus.unregister(this);
    }

    @Override
    public void getAllProducts(User user) {
        if (this.view != null){
            this.view.showProgressBar();
        }
        this.productListInteractor.getAllProducts(user);
    }

    @Override
    public void deleteProduct(User user, Product product) {
        this.productListInteractor.deleteProduct(user, product);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ProductListEvent event) {

        switch (event.getType()){
            case ProductListEvent.PRODUCT_SEARCH_SUCCESS:
                this.onSearchProductsSuccess(event.getProducts());
                break;
            case ProductListEvent.PRODUCT_DELETE_SUCCESS:
                this.onDeleteProductSuccess();
                break;

        }

    }

    private void onSearchProductsSuccess(List<Product> products) {
        if (view != null){
            this.view.setProducts(products);
            this.view.hideProgressBar();
        }
    }

    private void onDeleteProductSuccess(){
        if (view != null){
            this.view.onDeleteProductSuccess();
        }
    }
}
