package com.product.shop.productshop.productList.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    public ProductListAdapter(List<Product> products, ImageLoader imageLoader) {
        this.products = products;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolderProductList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_litst, parent, false);
        return new ViewHolderProductList(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductList holder, int position) {

        Product product = this.products.get(position);

        this.imageLoader.load(holder.imgProduct,product.getImageUrl());
        holder.txtProductName.setText(product.getTitle());
        holder.txtPrice.setText(String.valueOf(product.getPrice()));

    }

    public void setProducts(List<Product> products){
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

        private View view;

        public ViewHolderProductList(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this,this.view);
        }
    }
}
