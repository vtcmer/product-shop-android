package com.product.shop.productshop.productList.db;

import com.product.shop.productshop.model.Product;
import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vtcmer on 13/07/2016.
 */
@Database(name = ProductDataBase.NAME, version = ProductDataBase.VERSION)
public class ProductDataBase {

    public static final int VERSION = 1;
    public static final String NAME = "PRODUCTS";
}
