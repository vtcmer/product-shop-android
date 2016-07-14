package com.product.shop.productshop.login.impl;

import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.login.LoginInteractor;
import com.product.shop.productshop.login.LoginPresenter;
import com.product.shop.productshop.login.events.LoginEvent;
import com.product.shop.productshop.login.ui.LoginView;
import com.product.shop.productshop.model.User;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by vtcmer on 11/07/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.eventBus.unregister(this);
    }

    @Override
    public void login(String email, String password) {

        if (view != null){
            this.view.showProgressBar();
            this.view.lockComponents();
        }

        this.interactor.login(email, password);

    }

    @Override
    public void registerUser(String email, String password) {
        if (view != null){
            this.view.showProgressBar();
            this.view.lockComponents();
        }
        this.interactor.register(email,password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {

        final String msg = event.getMsg();

        switch (event.getType()){
            case LoginEvent.LOGIN_SUCCESS:
                this.onLoginSuccess(event.getUser());
                break;
            case LoginEvent.LOGIN_FAIL:
                this.onLoginFail(msg);
                break;
            case LoginEvent.REGISTER_SUCCESS:
                this.onRegisterSuccess();
                break;
            case LoginEvent.REGISTER_FAIL:
                this.onRegisterFail(msg);
                break;
        }

    }

    private void onLoginSuccess(final User user){
        if (view != null){
            this.view.hideProgressBar();
            this.view.unlockComponents();
            this.view.onLoginSuccess(user);
            this.view.redirectToProductListView();
        }
    }

    private void onLoginFail(final String error){
        if (view != null){
            this.view.hideProgressBar();
            this.view.unlockComponents();
            this.view.onLoginError(error);
        }
    }


    private void onRegisterSuccess(){
        if (view != null){
            this.view.hideProgressBar();
            this.view.unlockComponents();
            this.view.onRegisterSuccess();
        }
    }

    private void onRegisterFail(final String error){
        if (view != null){
            this.view.hideProgressBar();
            this.view.unlockComponents();
            this.view.onRegisterError(error);
        }
    }
}
