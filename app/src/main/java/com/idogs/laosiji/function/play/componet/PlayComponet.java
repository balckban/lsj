package com.idogs.laosiji.function.play.componet;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.function.description.DescriptionActivity;
import com.idogs.laosiji.function.description.module.DesModule;
import com.idogs.laosiji.function.play.PlayActivity;
import com.idogs.laosiji.function.play.module.PlayModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@ActivityScope
@Component(modules = PlayModule.class, dependencies = YbBasicComponent.class)
public interface PlayComponet {
      void  inject(PlayActivity playActivity);
}
