package com.product.shop.productshop.product;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productList.entities.UserProduct;
import com.product.shop.productshop.productList.entities.UserProduct_Table;
import com.product.shop.productshop.productList.events.ProductListEvent;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vtcmer on 16/07/2016.
 */
public abstract class ProductAbstractRepository {





    /**
     * Recuperación de un producto en concreto para un usuario
     * @param userId
     * @param productId
     * @return
     */
    protected UserProduct getProduct(final String userId, final String productId){

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

    protected Product toProduct(final UserProduct userProduct){

        Product product = null;
        if  (userProduct != null) {
            product = new Product();
            product.setProductId(userProduct.getProductId());
            product.setImageUrl(userProduct.getImageUrl());
            product.setPrice(userProduct.getPrice());
            product.setTitle(userProduct.getTitle());
            product.setUnits(userProduct.getUnits());
        }

        return product;

    }

    protected UserProduct toUserProduct(final User user,final Product product){
        UserProduct userProduct = null;
        if (product != null){
            userProduct = new UserProduct();
            userProduct.setEmail(user.getEmail());
            userProduct.setUserId(user.getUserId());
            userProduct.setProductId(product.getProductId());
            userProduct.setImageUrl(product.getImageUrl());
            userProduct.setPrice(product.getPrice());
            userProduct.setTitle(product.getTitle());
            userProduct.setUnits(product.getUnits());
        }

        return  userProduct;
    }


}
