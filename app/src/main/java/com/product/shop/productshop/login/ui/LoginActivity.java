package com.product.shop.productshop.login.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.product.shop.productshop.BuildConfig;
import com.product.shop.productshop.ProductShopApp;
import com.product.shop.productshop.R;
import com.product.shop.productshop.api.producs.ProductClient;
import com.product.shop.productshop.api.producs.ProductResults;
import com.product.shop.productshop.api.producs.ProductService;
import com.product.shop.productshop.login.LoginPresenter;
import com.product.shop.productshop.model.Product;

import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements  LoginView{

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout container;

    @Inject
    LoginPresenter presenter;

    ProductShopApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        app = (ProductShopApp) getApplication();

        this.setupInjection();

        this.presenter.onCreate();

        this.renderProducts();
    }

    private void setupInjection() {
        app.getLoginComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Evento que se ejecuta cuando se intenta autenticar el usuario
     */
    @OnClick(R.id.btnSignin)
    void siginUser(){
        this.presenter.login(this.inputEmail.getText().toString(), this.inputPassword.getText().toString());
    }


    /**
     * Evento que se ejecuta cuando el usuario se intenta registrar
     */
    @OnClick(R.id.btnSignup)
    void sigupUser(){
        this.presenter.registerUser(this.inputEmail.getText().toString(), this.inputPassword.getText().toString());
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
    public void lockComponents() {
        this.toogleComponents(false);
    }


    @Override
    public void unlockComponents() {
        this.toogleComponents(true);
    }

    @Override
    public void onLoginSuccess() {
        String msg = "Usuario Autenticado "+this.inputEmail.getText();
        Snackbar.make(this.container,msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoginError(String error) {

        this.inputPassword.setText("");
        final String msgError = String.format(getString(R.string.login_error_message_signin),error);
        this.inputPassword.setError(msgError);

    }

    @Override
    public void onRegisterSuccess() {
        String msg = "Usuario Registrado "+this.inputEmail.getText();
        Snackbar.make(this.container,msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRegisterError(String error) {
        this.inputPassword.setText("");
        final String msgError = String.format(getString(R.string.login_error_message_signup),error);
        this.inputPassword.setError(msgError);
    }


    /**
     * Bloquea y desbloquea los componentes
     * @param active
     */
    private void toogleComponents(final boolean active){
        this.inputEmail.setEnabled(active);
        this.inputPassword.setEnabled(active);
        this.btnSignin.setEnabled(active);
        this.btnSignup.setEnabled(active);
    }


    public void renderProducts(){

        String sort = "recipe_id";
        int count = 2;
        int page = 1;

        ProductClient client = new ProductClient();
        ProductService service = client.getService();

        Call<ProductResults> call = service.search(BuildConfig.FOOD_API_KEY,sort,count,page);

        Callback<ProductResults> callback = new Callback<ProductResults>() {
            @Override
            public void onResponse(Call<ProductResults> call, Response<ProductResults> response) {
                if (response.isSuccess()){
                    ProductResults results = response.body();

                    for (Product product: results.getProducts()){
                        product.setPrice(new Random().nextDouble()*100);
                    }
                    System.out.println();
                }
            }

            @Override
            public void onFailure(Call<ProductResults> call, Throwable t) {
                System.out.println();
            }
        };

        call.enqueue(callback);


    }


}
