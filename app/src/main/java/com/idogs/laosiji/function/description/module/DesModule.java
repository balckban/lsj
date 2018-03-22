package com.idogs.laosiji.function.description.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.function.description.contract.DesContract;
import com.idogs.laosiji.function.description.presenter.DesPresenter;
import com.idogs.laosiji.user.function.main.contract.UserContract;
import com.idogs.laosiji.user.function.main.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@Module
public class DesModule {

    private DesContract.View view;

    public DesModule(DesContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    DesContract.View providerView() {
        return view;
    }
    @ActivityScope
    @Provides
    DesContract.Presenter providePresenter(DesContract.View view){
       return new DesPresenter(view);
    }

}
