package com.product.shop.productshop.productCart.impl;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productCart.ProductCartInteractor;
import com.product.shop.productshop.productCart.ProductCartPresenter;
import com.product.shop.productshop.productCart.events.ProductCartEvent;
import com.product.shop.productshop.productCart.ui.ProductCartView;
import com.product.shop.productshop.productList.events.ProductListEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by vtcmer on 14/07/2016.
 */
public class ProductCartPresenterImpl implements ProductCartPresenter {

    private EventBus eventBus;
    private ProductCartView view;
    private ProductCartInteractor productCartInteractor;

    public ProductCartPresenterImpl(EventBus eventBus, ProductCartView view, ProductCartInteractor productCartInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.productCartInteractor = productCartInteractor;
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
        this.productCartInteractor.getAllProducts(user);
    }

    @Override
    public void deleteProduct(User user, Product product) {
        this.productCartInteractor.deleteProduct(user, product);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ProductCartEvent event) {

        switch (event.getType()){
            case ProductCartEvent.PRODUCT_SEARCH_SUCCESS:
                this.onSearchProductsSuccess(event.getProducts());
                break;
            case ProductCartEvent.PRODUCT_DELETE_SUCCESS:
                Product product = null;
                List<Product> products = event.getProducts();
                if (!products.isEmpty()){
                    product = event.getProducts().get(0);
                }

                this.onDeleteProductSuccess(product);
                break;

        }

    }

    private void onSearchProductsSuccess(List<Product> products) {
        if (view != null){
            this.view.setProducts(products);
            this.view.hideProgressBar();
        }
    }

    private void onDeleteProductSuccess(final Product product){
        if (view != null){
            this.view.onDeleteProductSuccess(product);
        }
    }
}
