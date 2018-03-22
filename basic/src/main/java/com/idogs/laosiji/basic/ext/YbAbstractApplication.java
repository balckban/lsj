package com.idogs.laosiji.basic.ext;

import com.idogs.laosiji.core.base.BaseApplication;

/**
 * Created by y on 2017/8/15.
 */

public abstract class YbAbstractApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO
        YbEstopCore.init(this);
        // 初始化环信
    }
}
