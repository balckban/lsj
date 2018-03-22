package com.idogs.laosiji.basic.module;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.github.aleksandermielczarek.napkin.scope.AppScope;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.core.module.CoreModule;
import com.idogs.laosiji.core.preference.field.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by y on 2017/8/15.
 */
@AppScope
@Module
public class YbBasicModule extends CoreModule {

    Application appContext;

    public YbBasicModule(Application appContext){
        super(appContext);
        this.appContext = appContext;
    }

    @AppScope
    @Provides
    YbAppPreference provideYbAppPreference(@App SPUtils spUtils){
        return new YbAppPreference(spUtils);
    }
}
