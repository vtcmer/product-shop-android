package com.product.shop.productshop.api.producs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class ProductClient {

    private Retrofit retrofit;


    private final static String BASE_URL = "http://food2fork.com/api/";

    public ProductClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public ProductService getService(){
        return this.retrofit.create(ProductService.class);
    }
}
