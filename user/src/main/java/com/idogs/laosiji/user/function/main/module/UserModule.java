package com.idogs.laosiji.user.function.main.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.user.function.main.contract.UserContract;
import com.idogs.laosiji.user.function.main.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@Module
public class UserModule {

    private UserContract.View view;

    public UserModule(UserContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View providerView() {
        return view;
    }
    @ActivityScope
    @Provides
    UserContract.Presenter providePresenter(UserContract.View view){
       return new UserPresenter(view);
    }

}
