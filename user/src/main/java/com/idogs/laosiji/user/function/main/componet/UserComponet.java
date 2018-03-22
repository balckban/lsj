package com.idogs.laosiji.user.function.main.componet;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.user.function.main.SignupActivity;
import com.idogs.laosiji.user.function.main.UserLoginActivity;
import com.idogs.laosiji.user.function.main.module.UserModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@ActivityScope
@Component(modules = UserModule.class, dependencies = YbBasicComponent.class)
public interface UserComponet  {
    void inject(UserLoginActivity loginActivity);
    void inject(SignupActivity signupActivity);
}
