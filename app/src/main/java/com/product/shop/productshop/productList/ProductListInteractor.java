package com.product.shop.productshop.productList;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 12/07/2016.
 */
public interface ProductListInteractor {

    /**
     * Carga la lista de productos
     */
    void loadProductList();

    void addProduct(final User user, final Product product);
}
