package com.idogs.laosiji.function.search.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.function.description.contract.DesContract;
import com.idogs.laosiji.function.description.presenter.DesPresenter;
import com.idogs.laosiji.function.search.contract.SearchContract;
import com.idogs.laosiji.function.search.presenter.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@Module
public class SearchModule {

    private SearchContract.View view;

    public SearchModule(SearchContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    SearchContract.View providerView() {
        return view;
    }
    @ActivityScope
    @Provides
    SearchContract.Presenter providePresenter(SearchContract.View view){
       return new SearchPresenter(view);
    }

}
