package com.product.shop.productshop.productList;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 12/07/2016.
 */
public interface ProductListRepository {

    /**
     * Carga la lista de productos
     */
    void loadProductList();

    /**
     * Añadir un producto para un usuario
     * @param user
     * @param product
     */
    void addProduct(final User user, final Product product);


}
