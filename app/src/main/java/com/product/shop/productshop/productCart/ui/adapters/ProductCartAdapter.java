package com.product.shop.productshop.productCart.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.product.shop.productshop.R;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.productCart.ui.SwipeGestureDetector;
import com.product.shop.productshop.productCart.ui.SwiteGestureListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vtcmer on 14/07/2016.
 */
public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ViewHolderProductCart>   {



    private List<Product> products;
    private ImageLoader imageLoader;
    private Context context;
    private OnItemClickListenerProductCart onItemClickListenerProductCart;


    public ProductCartAdapter(Context context, List<Product> products, ImageLoader imageLoader, OnItemClickListenerProductCart onItemClickListenerProductCart) {
        this.context = context;
        this.products = products;
        this.imageLoader = imageLoader;
        this.onItemClickListenerProductCart = onItemClickListenerProductCart;
    }

    @Override
    public ViewHolderProductCart onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart, parent, false);
        return new ViewHolderProductCart(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductCart holder, int position) {
        Product product = this.products.get(position);

        this.imageLoader.load(holder.imgProduct, product.getImageUrl());

        int titleSize = 15;
        if (product.getTitle().length() < titleSize){
            titleSize = product.getTitle().length();
        }

        holder.txtTitle.setText(product.getTitle().substring(0,titleSize)+"...");
        holder.txtPrice.setText(String.valueOf(product.getPrice())+ " €");
        holder.txtUnit.setText(String.valueOf(product.getUnits())+ " Unidades");


        if(position%2==0) {
            holder.view.setBackgroundColor(Color.LTGRAY);
        }

        holder.setupGestureDetector(product,context, onItemClickListenerProductCart);


    }





    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void removeProduct(final Product product){
        this.products.remove(product);
        notifyDataSetChanged();
    }



    static class ViewHolderProductCart extends RecyclerView.ViewHolder {

        @Bind(R.id.imgProduct)
        ImageView imgProduct;
        @Bind(R.id.layoutButtons)
        LinearLayout layoutButtons;
        @Bind(R.id.txtTitle)
        TextView txtTitle;
        @Bind(R.id.txtUnit)
        TextView txtUnit;
        @Bind(R.id.txtPrice)
        TextView txtPrice;
        private View view;

        public ViewHolderProductCart(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, this.view);
        }

        void setupGestureDetector(final Product product,final Context context, final OnItemClickListenerProductCart onItemClickListenerProductCart ){

            final GestureDetector detector = new GestureDetector(context,new SwipeGestureDetector(new SwiteGestureListener() {

                @Override
                public void onDismiss() {
                    onItemClickListenerProductCart.onSwipeRemove(product);
                }
            }));
            View.OnTouchListener touchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return detector.onTouchEvent(event);
                }
            };

            this.view.setOnTouchListener(touchListener);
        }
    }
}
