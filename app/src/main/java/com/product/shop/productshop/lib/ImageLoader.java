package com.product.shop.productshop.lib;

import android.widget.ImageView;


public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
