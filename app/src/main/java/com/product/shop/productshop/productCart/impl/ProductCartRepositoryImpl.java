package com.product.shop.productshop.productCart.impl;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.product.ProductAbstractRepository;
import com.product.shop.productshop.productCart.ProductCartRepository;
import com.product.shop.productshop.productCart.events.ProductCartEvent;
import com.product.shop.productshop.productList.entities.UserProduct;
import com.product.shop.productshop.productList.entities.UserProduct_Table;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vtcmer on 16/07/2016.
 */
public class ProductCartRepositoryImpl extends ProductAbstractRepository implements ProductCartRepository {

    private EventBus eventBus;

    public ProductCartRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getAllProducts(final User user) {

        List<Product> products = new ArrayList<Product>();

        List<UserProduct> userProducts = new Select().from(UserProduct.class)
                .where(UserProduct_Table.userId.eq(user.getUserId()))
                .queryList();

        if (userProducts != null){

            for (UserProduct userProduct: userProducts){
                Product product = this.toProduct(userProduct);
                products.add(product);

            }

        }

        postProducts(ProductCartEvent.PRODUCT_SEARCH_SUCCESS,products);
    }



    @Override
    public void deleteProduct(User user, Product product) {

        UserProduct userProduct = this.getProduct(user.getUserId(),product.getProductId());
        List<Product> products = new ArrayList<Product>();
        if (userProduct != null){
            userProduct.delete();
            products.add(product);
        }
        postProducts(ProductCartEvent.PRODUCT_DELETE_SUCCESS,products);

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


    /**
     * Envio del evento
     * @param type
     * @param products
     * @param msg
     */
    private void post(final int type, final List<Product> products, final String msg){
        ProductCartEvent event = new ProductCartEvent();
        event.setType(type);
        event.setMsg(msg);
        event.setProducts(products);

        eventBus.post(event);
    }
}
