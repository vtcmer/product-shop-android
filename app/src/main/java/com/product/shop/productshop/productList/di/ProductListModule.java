package com.product.shop.productshop.productList.di;

import com.product.shop.productshop.api.firebase.FirebaseApi;
import com.product.shop.productshop.api.producs.ProductClient;
import com.product.shop.productshop.api.producs.ProductService;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.productList.ProductListInteractor;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.ProductListRepository;
import com.product.shop.productshop.productList.SessionInteractor;
import com.product.shop.productshop.productList.SessionRepository;
import com.product.shop.productshop.productList.impl.ProductListInteractorImpl;
import com.product.shop.productshop.productList.impl.ProductListPresenterImpl;
import com.product.shop.productshop.productList.impl.ProductListRepositoryImpl;
import com.product.shop.productshop.productList.impl.SessionInteractorImpl;
import com.product.shop.productshop.productList.impl.SessionRepositoryImpl;
import com.product.shop.productshop.productList.ui.ProductListView;
import com.product.shop.productshop.productList.ui.adapters.OnItemClickListenerProductList;
import com.product.shop.productshop.productList.ui.adapters.ProductListAdapter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 12/07/2016.
 */
@Module
public class ProductListModule {

    private ProductListView view;
    private OnItemClickListenerProductList onItemClickListenerProductList;

    public ProductListModule(ProductListView view, OnItemClickListenerProductList onItemClickListenerProductList) {
        this.view = view;
        this.onItemClickListenerProductList = onItemClickListenerProductList;
    }

    @Singleton
    @Provides
    ProductListView providesProductListView(){
        return this.view;
    }


    @Singleton
    @Provides
    OnItemClickListenerProductList providesOnItemClickListenerProductList(){
        return this.onItemClickListenerProductList;
    }


    @Singleton
    @Provides
    ProductListPresenter providesProductListPresenter(EventBus eventBus, ProductListView view, ProductListInteractor productListInteractor, SessionInteractor sessionInteractor){
        return new ProductListPresenterImpl(eventBus, view, productListInteractor, sessionInteractor);
    }

    @Singleton
    @Provides
    ProductListAdapter providesProductListAdapter(List<Product> products, ImageLoader imageLoader, OnItemClickListenerProductList onItemClickListenerProductList){
        return new ProductListAdapter(products,imageLoader,onItemClickListenerProductList);
    }


    @Singleton
    @Provides
    ProductListInteractor providesProductListInteractor(ProductListRepository repository){
        return new ProductListInteractorImpl(repository);
    }

    @Singleton
    @Provides
    ProductListRepository provicesProductListRepository(EventBus eventBus, ProductService productService){
        return new ProductListRepositoryImpl(eventBus, productService);
    }


    @Singleton
    @Provides
    ProductService providesProductService(){
        ProductClient client = new ProductClient();
        return client.getService();
    }

    @Singleton
    @Provides
    SessionInteractor providesSessionInteractor(final SessionRepository repository){
        return new SessionInteractorImpl(repository);
    }

    @Singleton
    @Provides
    SessionRepository providesSessionRepository(final FirebaseApi firebaseApi){
        return new SessionRepositoryImpl(firebaseApi);
    }



}
