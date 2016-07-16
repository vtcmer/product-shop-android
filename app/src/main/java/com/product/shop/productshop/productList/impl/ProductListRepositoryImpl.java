package com.product.shop.productshop.productList.impl;

import com.product.shop.productshop.BuildConfig;
import com.product.shop.productshop.api.producs.ProductResults;
import com.product.shop.productshop.api.producs.ProductService;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
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
public class ProductListRepositoryImpl implements ProductListRepository {

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
    public void getAllProducts(final User user) {

        List<Product> products = new ArrayList<Product>();

        List<UserProduct> userProducts = new Select().from(UserProduct.class)
                .where(UserProduct_Table.userId.eq(user.getUserId()))
                .queryList();

        if (userProducts != null){

            for (UserProduct userProduct: userProducts){
                Product product = new Product();
                product.setProductId(userProduct.getProductId());
                product.setImageUrl(userProduct.getImageUrl());
                product.setPrice(userProduct.getPrice());
                product.setTitle(userProduct.getTitle());
                product.setUnits(userProduct.getUnits());
                products.add(product);

            }

        }

        postProducts(ProductListEvent.PRODUCT_SEARCH_SUCCESS,products);
    }




    @Override
    public void addProduct(final User user, Product product) {

        UserProduct userProduct =  getProduct(user.getUserId(), product.getProductId());
        if (userProduct == null) {
            userProduct = new UserProduct();
            userProduct.setEmail(user.getEmail());
            userProduct.setUserId(user.getUserId());
            userProduct.setProductId(product.getProductId());
            userProduct.setImageUrl(product.getImageUrl());
            userProduct.setPrice(product.getPrice());
            userProduct.setTitle(product.getTitle());
            userProduct.setUnits(1);
            userProduct.save();
        } else {
            userProduct.setUnits(userProduct.getUnits()+1);
            userProduct.update();
        }

        post(ProductListEvent.PRODUCT_ADDED_SUCCESS);

    }

    @Override
    public void deleteProduct(User user, Product product) {

        UserProduct userProduct = this.getProduct(user.getUserId(),product.getProductId());
        if (userProduct != null){
            userProduct.delete();
        }
        post(ProductListEvent.PRODUCT_DELETE_SUCCESS);

    }


    /**
     * Recuperación de un producto en concreto para un usuario
     * @param userId
     * @param productId
     * @return
     */
    private UserProduct getProduct(final String userId, final String productId){

        UserProduct userProduct = null;

        List<UserProduct> userProducts = new Select().from(UserProduct.class)
                .where(UserProduct_Table.userId.eq(userId)
                        ,UserProduct_Table.productId.eq(productId))
                .queryList();

        if (userProducts != null && (userProducts.size() > 0)){
            userProduct = userProducts.get(0);
        }

        return userProduct;
    }

    private void post(final int type){
        this.post(type,null);
    }

    private void post(final int type,final String msg){
        this.post(type,null,msg);
    }

    private void postProducts(final int type,final List<Product> products){
        this.post(type,products,null);
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
