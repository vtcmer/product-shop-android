package com.product.shop.productshop.productList.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.product.shop.productshop.R;
import com.product.shop.productshop.lib.ImageLoader;
import com.product.shop.productshop.model.Product;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vtcmer on 12/07/2016.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolderProductList> {


    private List<Product> products;
    private ImageLoader imageLoader;
    private OnItemClickListenerProductList onItemClickListenerProductList;

    public ProductListAdapter(List<Product> products, ImageLoader imageLoader, OnItemClickListenerProductList onItemClickListenerProductList) {
        this.products = products;
        this.imageLoader = imageLoader;
        this.onItemClickListenerProductList = onItemClickListenerProductList;
    }

    @Override
    public ViewHolderProductList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_litst, parent, false);
        return new ViewHolderProductList(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductList holder, int position) {

        Product product = this.products.get(position);

        this.imageLoader.load(holder.imgProduct, product.getImageUrl());

        int titleSize = 15;
        if (product.getTitle().length() < titleSize){
            titleSize = product.getTitle().length();
        }

        holder.txtProductName.setText(product.getTitle().substring(0,titleSize)+"...");
        holder.txtPrice.setText(String.valueOf(product.getPrice())+ " €");

        holder.setOnItemClickListener(product, onItemClickListenerProductList);

    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    static class ViewHolderProductList extends RecyclerView.ViewHolder {


        @Bind(R.id.imgProduct)
        ImageView imgProduct;
        @Bind(R.id.txtProductName)
        TextView txtProductName;
        @Bind(R.id.txtPrice)
        TextView txtPrice;
        @Bind(R.id.btnAddProduct)
        ImageButton btnAddProduct;

        private View view;

        public ViewHolderProductList(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, this.view);
        }

        public void setOnItemClickListener(final Product product, final OnItemClickListenerProductList onItemClickListenerProductList) {

            btnAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListenerProductList.onItemClick(product);
                }
            });

        }
    }
}
