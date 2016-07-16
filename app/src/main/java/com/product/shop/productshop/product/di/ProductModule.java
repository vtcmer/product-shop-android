package com.product.shop.productshop.product.di;

import com.product.shop.productshop.api.producs.ProductClient;
import com.product.shop.productshop.api.producs.ProductService;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.productList.ProductListInteractor;
import com.product.shop.productshop.productList.ProductListRepository;
import com.product.shop.productshop.productList.impl.ProductListInteractorImpl;
import com.product.shop.productshop.productList.impl.ProductListRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 14/07/2016.
 */
@Module
public class ProductModule {


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
    List<Product> providesProductList(){
        return new ArrayList<Product>();
    }


}
