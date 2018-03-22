package com.idogs.laosiji.basic.component;

import com.github.aleksandermielczarek.napkin.scope.AppScope;
import com.idogs.laosiji.basic.module.YbBasicModule;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.core.component.CoreComponent;

import dagger.Component;

/**
 * Created by y on 2017/8/15.
 */
@AppScope
@Component(modules = YbBasicModule.class)
public interface YbBasicComponent extends CoreComponent {

    YbAppPreference ybAppPreference();
}
