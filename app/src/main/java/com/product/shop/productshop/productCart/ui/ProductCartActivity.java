package com.product.shop.productshop.productCart.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.R;
import com.product.shop.productshop.lib.UserService;
import com.product.shop.productshop.model.Product;
import com.product.shop.productshop.model.User;
import com.product.shop.productshop.productCart.ProductCartPresenter;
import com.product.shop.productshop.productCart.di.ProductCartComponent;
import com.product.shop.productshop.productCart.ui.adapters.OnItemClickListenerProductCart;
import com.product.shop.productshop.productCart.ui.adapters.ProductCartAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductCartActivity extends AppCompatActivity implements ProductCartView ,OnItemClickListenerProductCart {


    ProductCartPresenter presenter;
    ProductCartAdapter adapter;
    UserService userService;

    ProductShopApp app;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBarCart)
    ProgressBar progressBar;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);
        ButterKnife.bind(this);

        app = (ProductShopApp) getApplication();

        this.setupInjection();
        this.setupRecyclerView();

        this.presenter.onCreate();
        user = this.userService.getUserAuth();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.getAllProducts(user);

    }

    private void setupRecyclerView() {
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void setupInjection() {

        ProductCartComponent component = app.getProductCartComponent(this, this, this);
        this.presenter = component.getPresenter();
        this.adapter = component.getAdapter();
        this.userService = component.getUserService();

    }

    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
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
    public void onDeleteProductSuccess(Product product) {
        Snackbar.make(this.mainContent, R.string.product_delete_success, Snackbar.LENGTH_LONG).show();
        this.adapter.removeProduct(product);
    }

    @Override
    public void onSwipeRemove(Product product) {
       this.presenter.deleteProduct(this.user,product);
    }
}
