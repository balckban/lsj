package com.idogs.laosiji.function.main.module;


import android.support.v7.app.AppCompatActivity;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.idogs.laosiji.function.main.presenter.MainAppPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
@Module
public class MainAppModule {

    private MainContract.View view;
    private AppCompatActivity context;

    public MainAppModule(MainContract.View view){
        this.view = view;
    }

    public MainAppModule(MainContract.View view,AppCompatActivity activity){
        this.view = view;
        this.context=activity;
    }

    @ActivityScope
    @Provides
    MainContract.View providerView() {
        return view;
    }
    @ActivityScope
    @Provides
    MainContract.Presenter providePresenter(MainContract.View view){
        return new MainAppPresenterImpl(view,context);
    }
}
