package com.product.shop.productshop.productList.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.R;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.di.ProductListComponent;
import com.product.shop.productshop.productList.ui.adapters.ProductListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity implements ProductListView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    ProductListPresenter presenter;
    ProductListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        this.setupToolBar();
        this.setupInjection();
        this.setupRecyclerView();

        presenter.onCreate();
        presenter.loadProductList();

    }

    private void setupRecyclerView() {

        this.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        this.recyclerView.setAdapter(this.adapter);
    }


    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }


    @OnClick(R.id.toolbar)
    public void onToolBarClick() {
        this.recyclerView.smoothScrollToPosition(0);
    }


    private void setupToolBar() {
        setSupportActionBar(this.toolbar);
    }


    private void setupInjection() {

        ProductShopApp app = (ProductShopApp) getApplication();
        ProductListComponent component = app.getProductListComponent(this,this);
        this.presenter = component.getProductListPresenter();
        this.adapter = component.getProductListAdapter();
    }

    @Override
    public void showProgressBar() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setProducts(List<Product> products) {
        this.adapter.setProducts(products);
    }
}
