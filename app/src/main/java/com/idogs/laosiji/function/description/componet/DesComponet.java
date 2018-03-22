package com.idogs.laosiji.function.description.componet;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.function.description.DescriptionActivity;
import com.idogs.laosiji.function.description.module.DesModule;
import com.idogs.laosiji.user.function.main.SignupActivity;
import com.idogs.laosiji.user.function.main.UserLoginActivity;
import com.idogs.laosiji.user.function.main.module.UserModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@ActivityScope
@Component(modules = DesModule.class, dependencies = YbBasicComponent.class)
public interface DesComponet {
      void  inject(DescriptionActivity descriptionActivity);
}
