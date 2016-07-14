package com.product.shop.productshop.productList.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.R;
import com.product.shop.productshop.lib.UserService;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productList.ProductListPresenter;
import com.product.shop.productshop.productList.di.ProductListComponent;
import com.product.shop.productshop.productList.ui.adapters.OnItemClickListenerProductList;
import com.product.shop.productshop.productList.ui.adapters.ProductListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity implements ProductListView, OnItemClickListenerProductList {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    ProductListPresenter presenter;
    ProductListAdapter adapter;
    UserService userService;

    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.myProducts){
            this.navigateMyProducts();
        } else if (id == R.id.action_logout){
            this.logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
    }

    private void navigateMyProducts() {
    }

    private void setupRecyclerView() {

        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
        ProductListComponent component = app.getProductListComponent(this, this, this);
        this.presenter = component.getProductListPresenter();
        this.adapter = component.getProductListAdapter();
        this.userService = component.getUserService();
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

    @Override
    public void onProductAddSuccess() {
        String msg = "Product Añadido correctamente ";
        Snackbar.make(this.mainContent, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Product product) {
        User user = userService.getUserAuth();
        this.presenter.addProduct(user, product);
    }
}
