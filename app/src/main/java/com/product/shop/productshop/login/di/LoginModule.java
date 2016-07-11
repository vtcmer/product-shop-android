package com.product.shop.productshop.login.di;

import com.product.shop.productshop.api.firebase.FirebaseApi;
import com.product.shop.productshop.lib.EventBus;
import com.product.shop.productshop.login.LoginInteractor;
import com.product.shop.productshop.login.LoginPresenter;
import com.product.shop.productshop.login.LoginRepository;
import com.product.shop.productshop.login.impl.LoginInteractorImpl;
import com.product.shop.productshop.login.impl.LoginPresenterImpl;
import com.product.shop.productshop.login.impl.LoginRepositoryImpl;
import com.product.shop.productshop.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 11/07/2016.
 */
@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    public LoginView providesLoginView(){
        return view;
    }

    @Singleton
    @Provides
    public LoginPresenter providesLoginPresenter(final EventBus eventBus, final LoginView view, final LoginInteractor interactor){
        return new LoginPresenterImpl(eventBus,view, interactor);
    }

    @Singleton
    @Provides
    public LoginInteractor providesLoginInteractor(final LoginRepository repository){
        return new LoginInteractorImpl(repository);
    }

    @Singleton
    @Provides
    public LoginRepository providesLoginRepository(final EventBus eventBus, final FirebaseApi firebaseApi){
        return new LoginRepositoryImpl(eventBus,firebaseApi);
    }
}
