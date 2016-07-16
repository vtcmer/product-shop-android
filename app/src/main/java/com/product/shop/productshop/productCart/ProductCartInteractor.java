package com.product.shop.productshop.productCart;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;

/**
 * Created by vtcmer on 16/07/2016.
 */
public interface ProductCartInteractor {

    /**
     * Recuperación de todos los productos de un usuario
     * @param user
     */
    void  getAllProducts(final User user);


    /**
     * Eliminar un producto para un usuario
     * @param user
     * @param product
     */
    void deleteProduct(final User user, final Product product);
}
