package com.idogs.laosiji.http.component;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.github.aleksandermielczarek.napkin.scope.UserScope;
import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.core.component.NetComponent;
import com.idogs.laosiji.function.description.presenter.DesPresenter;
import com.idogs.laosiji.function.main.presenter.MainAppPresenterImpl;
import com.idogs.laosiji.function.play.presenter.PlayPresenter;
import com.idogs.laosiji.function.search.presenter.SearchPresenter;
import com.idogs.laosiji.http.module.IdogsRetrofitModule;
import com.idogs.laosiji.update.UpdateManager;
import com.idogs.laosiji.update.http.module.UpdateRetrofitModule;


import dagger.Component;

/**
 * <b>类名称：</b> IdogsComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 13:58<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@ActivityScope
@Component(modules = IdogsRetrofitModule.class, dependencies = YbNetworkComponent.class)
public interface IdogsComponent {
    void inject(MainAppPresenterImpl mainAppPresenter);
    void inject(DesPresenter desPresenter);
    void inject(PlayPresenter playPresenter);
    void inject(SearchPresenter searchPresenter);
}
