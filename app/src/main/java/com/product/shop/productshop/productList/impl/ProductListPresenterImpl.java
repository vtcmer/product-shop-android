package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productList.ProductListInteractor;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.events.ProductListEvent;
import com.product.shop.productshop.productList.ui.ProductListView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by vtcmer on 12/07/2016.
 */
public class ProductListPresenterImpl implements ProductListPresenter {

    private EventBus eventBus;
    private ProductListView view;
    private ProductListInteractor interactor;

    public ProductListPresenterImpl(EventBus eventBus, ProductListView view, ProductListInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
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
    public void loadProductList() {
        if (view != null){
            this.view.showProgressBar();
        }
        this.interactor.loadProductList();
    }

    @Override
    public void addProduct(User user, Product product) {
        this.interactor.addProduct(user,product);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ProductListEvent event) {

        String msg = event.getMsg();
        switch (event.getType()){
            case ProductListEvent.SUCCESS:
                onSuccess(event.getProducts());
                break;
            case ProductListEvent.ERROR:
                onError(event.getMsg());
                break;
            case ProductListEvent.PRODUCT_ADDED_SUCCESS:
                onProductAddedSuccess();
                break;
        }

    }

    private void onSuccess(final List<Product> products){
        if (view != null){
            this.view.hideProgressBar();
            this.view.setProducts(products);

        }
    }

    private void onError(final String msg){
        if (view != null){
            this.view.hideProgressBar();
        }
    }


    private void onProductAddedSuccess(){
        if (view != null){
            this.view.onProductAddSuccess();
        }
    }
}
