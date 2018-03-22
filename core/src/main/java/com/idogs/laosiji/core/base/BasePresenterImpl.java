package com.idogs.laosiji.core.base;

import com.idogs.laosiji.core.component.NetComponent;
import com.idogs.laosiji.core.core.UnrealCore;
import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * <b>类名称：</b> BasePresenterImpl <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 16:27<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view;

    private LifecycleTransformer<Object> objectLifecycleTransformer;

    public BasePresenterImpl(V view) {
        this.view = view;
        injectNetComponent(UnrealCore.getNetComponent());
    }

    public abstract void injectNetComponent(NetComponent netComponent);

    @Override
    public void bindLifeCycle(LifecycleTransformer<Object> objectLifecycleTransformer) {
        this.objectLifecycleTransformer = objectLifecycleTransformer;
    }
}