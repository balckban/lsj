package com.idogs.laosiji.basic.http.token;


import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.basic.http.covert.YbLocalResponseTransformer;
import com.idogs.laosiji.basic.util.TipUtil;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.config.UrlConfig;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 带有Token处理的观察者
 * Created by y on 2017/8/15.
 */

public abstract class YbTokenObersver implements Observer {

    public YbTokenObersver(String disposeName) {
        this.disposeName = disposeName;
    }


    /**
     * Token网络请求方法
     */
    @Inject
    TokenServer tokenServer;

    /**
     * 原始被观察对象
     */
    Single mObservable;

    /**
     * 原始观察者
     */
    Consumer mObserver;

    /**
     * View视图
     */
    public YbView view;

    /**
     *
     */
    WeakReference<YbAbstractActivity> activityRef;

    String disposeName;


    @Override
    public void onError(Throwable e) {
        //TOKEN非法
        if (TipUtil.tokenIllegal.equals(e.getMessage())) {
            ARouter.getInstance().build(RouterConfig.USER_LOGIN).withBoolean("gomain", false).navigation();
            return;
        }
        //TOKEN过期
        if (TipUtil.tokenTimeout.equals(e.getMessage())) {

            tokenServer.getToken(UrlConfig.DEVICETYPE)
                    .compose(new YbLocalResponseTransformer<>())
                    .doOnSubscribe(disposable -> {
                        if (activityRef.get() != null) {
                            activityRef.get().setRequestTag("TOKEN_REQ", disposable);
                        }
                    }).subscribe(token -> {
                        if (activityRef.get() != null) {
//                            activityRef.get().ybAppPreference.saveToken(token);//保存TOKEN
                            activityRef.get().cancelRequest(this.disposeName);//重新请求
                            Disposable d = this.mObservable.subscribe(this.mObserver);
                            activityRef.get().setRequestTag(this.disposeName, d);
                        }
                    }, throwable -> {
                        if (activityRef.get() != null) {
                            activityRef.get().cancelRequest("TOKEN_REQ");
                            activityRef.get().cancelRequest(this.disposeName);
                        }
                    }
            );
        }
        afterErrorOpt();
    }

    /**
     * 设置前一个网络请求的被观察对象
     *
     * @param single
     * @return
     */
    public YbTokenObersver setObservable(Single single) {
        this.mObservable = mObservable;
        return this;
    }

    /**
     * 设置前一个网络请求的观察者
     *
     * @return
     */
    public YbTokenObersver setObserve(Consumer consumer) {
        this.mObserver = mObserver;
        return this;
    }

    /**
     * 定义错误操作以后的回调
     */
    public abstract void afterErrorOpt();

}
