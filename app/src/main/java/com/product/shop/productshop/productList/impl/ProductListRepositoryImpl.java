package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.BuildConfig;
import com.product.shop.productshop.api.producs.ProductResults;
import com.product.shop.productshop.api.producs.ProductService;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.product.ProductAbstractRepository;
import com.product.shop.productshop.productList.ProductListRepository;
import com.product.shop.productshop.productList.entities.UserProduct;
import com.product.shop.productshop.productList.entities.UserProduct_Table;
import com.product.shop.productshop.productList.events.ProductListEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vtcmer on 12/07/2016.
 */
public class ProductListRepositoryImpl extends ProductAbstractRepository implements ProductListRepository {

    private EventBus eventBus;
    private ProductService productService;

    public ProductListRepositoryImpl(EventBus eventBus, ProductService productService) {
        this.eventBus = eventBus;
        this.productService = productService;
    }

    @Override
    public void loadProductList() {

        String sort = "recipe_id";
        int count = 10;
        int page = 1;

        Call<ProductResults> call = productService.search(BuildConfig.FOOD_API_KEY,sort,count,page);

        Callback<ProductResults> callback = new Callback<ProductResults>() {
            @Override
            public void onResponse(Call<ProductResults> call, Response<ProductResults> response) {
                if (response.isSuccess()){
                    ProductResults results = response.body();

                    if (results.getCount() > 0) {
                        for (Product product : results.getProducts()) {

                            double price = new Random().nextInt(1000);

                            String format = new DecimalFormat("#.##").format(price);

                            product.setPrice(Double.valueOf(format));
                        }
                    } else {
                        if(results.getProducts() == null){
                            results.setProducts(new ArrayList<Product>());
                        }
                    }

                    post(results.getProducts());
                }else{
                    post(ProductListEvent.ERROR,response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductResults> call, Throwable t) {
                post(ProductListEvent.ERROR,t.getLocalizedMessage());
            }
        };

        call.enqueue(callback);

    }

    @Override
    public void addProduct(final User user, Product product) {

        UserProduct userProduct =  getProduct(user.getUserId(), product.getProductId());
        if (userProduct == null) {
            userProduct = toUserProduct(user,product);
            userProduct.setUnits(1);
            userProduct.save();
        } else {
            userProduct.setPrice(product.getPrice());
            userProduct.setUnits(userProduct.getUnits()+1);
            userProduct.update();
        }

        post(ProductListEvent.PRODUCT_ADDED_SUCCESS);

    }


    private void post(final int type){
        this.post(type,null);
    }

    private void post(final int type,final String msg){
        this.post(type,null,msg);
    }


    private void post(final List<Product> products){
        this.post(ProductListEvent.SUCCESS,products,null);
    }
    /**
     * Envio del evento
     * @param type
     * @param products
     * @param msg
     */
    private void post(final int type, final List<Product> products, final String msg){
        ProductListEvent event = new ProductListEvent();
        event.setType(type);
        event.setMsg(msg);
        event.setProducts(products);

        eventBus.post(event);
    }
}
