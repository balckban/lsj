package com.idogs.laosiji.application;

import com.idogs.laosiji.BuildConfig;
import com.idogs.laosiji.basic.ext.YbAbstractApplication;

/**
 * <b>类名称：</b> CustomerApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 15:47<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class CustomerApplication extends YbAbstractApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
