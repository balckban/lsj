package com.idogs.laosiji.basic.ext;

import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.core.base.BasePresenterImpl;
import com.idogs.laosiji.core.component.NetComponent;

/**
 * yb实现presenter的扩展类
 * Created by y on 2017/8/15.
 */

public abstract class YbPresenterImpl<V extends YbView> extends BasePresenterImpl<V> {

    public YbPresenterImpl(V view) {
        super(view);
        injectNetComponet2(YbEstopCore.getNetworkComponent());
    }


    @Override
    public void injectNetComponent(NetComponent netComponent) {
        //TODO
        /**
         * 不需要注入netcomponent对象，注入YbTokenComponent子类对象
         */
    }

    public abstract void injectNetComponet2(YbNetworkComponent component);
}
