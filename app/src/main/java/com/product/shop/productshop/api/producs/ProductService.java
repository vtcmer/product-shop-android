package com.product.shop.productshop.api.producs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vtcmer on 11/07/2016.
 */
public interface ProductService {

    @GET("search")
    Call<ProductResults> search(@Query("key")String key
            , @Query("sort")String sort
            , @Query("count") int count
            , @Query("page") int page);
}
