package com.idogs.laosiji.function.main.componet;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.function.main.MainActivity;
import com.idogs.laosiji.function.main.fragment.recFragment;
import com.idogs.laosiji.function.main.module.MainAppModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

@ActivityScope
@Component(modules = MainAppModule.class, dependencies = YbBasicComponent.class)
public interface MainAppComponet {
    void inject(MainActivity mainActivity);
    void inject(recFragment recFragment);

}
