package com.idogs.laosiji.function.play.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.function.description.contract.DesContract;
import com.idogs.laosiji.function.description.presenter.DesPresenter;
import com.idogs.laosiji.function.play.contract.PlayContract;
import com.idogs.laosiji.function.play.presenter.PlayPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@Module
public class PlayModule {

    private PlayContract.View view;

    public PlayModule(PlayContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    PlayContract.View providerView() {
        return view;
    }
    @ActivityScope
    @Provides
    PlayContract.Presenter providePresenter(PlayContract.View view){
       return new PlayPresenter(view);
    }

}
