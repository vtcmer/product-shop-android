package com.product.shop.productshop.productList;

import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productList.events.ProductListEvent;

/**
 * Created by vtcmer on 12/07/2016.
 */
public interface ProductListPresenter {

    void onCreate();
    void onDestroy();

    /**
     * Carga la lista de productos
     */
    void loadProductList();

    /**
     * Añade un product seleccionado
     * @param product
     */
    void addProduct(User user, Product product);

    /**
     * Se elimina la sessión
     */
    void logout();

    /**
     * Captura de los eventos
     * @param event
     */
    void onEventMainThread(final ProductListEvent event);
}
