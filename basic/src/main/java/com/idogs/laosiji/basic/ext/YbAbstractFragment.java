package com.idogs.laosiji.basic.ext;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.basic.util.CommonDialogUtil;
import com.idogs.laosiji.basic.util.TipUtil;
import com.idogs.laosiji.core.base.BaseFragment;
import com.idogs.laosiji.core.base.BasePresenter;
import com.idogs.laosiji.core.component.CoreComponent;
import com.idogs.laosiji.widget.window.WaitScreen;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by y on 2017/8/15.
 */

public abstract class YbAbstractFragment<P extends BasePresenter> extends BaseFragment<P> implements YbView,YbDisposeRequest {

    @Inject
    public YbAppPreference ybAppPreference;
    /**
     * 取消对象
     */
    public Map<String,WeakReference<Disposable>> requestMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (requestMap == null){
            synchronized (YbAbstractActivity.class){
                if (requestMap == null){
                    requestMap = new HashMap<>();
                }
            }
        }
    }

    /**
     * 保存请求标记，用于取消请求回调
     * @param tag
     * @param d
     */
    @Override
    public void setRequestTag(String tag,Disposable d){
        if(requestMap != null){
            WeakReference<Disposable> weakReference = new WeakReference<Disposable>(d);
            requestMap.put(tag,weakReference);
            compositeDisposable.add(d);
        }
    }

    /**
     * 请求的回调取消
     * @param tag
     * @return
     */
    @Override
    public boolean cancelRequest(String tag){
        if(requestMap != null){
            WeakReference<Disposable> weakReference = requestMap.get(tag);
            if(weakReference != null && weakReference.get()!= null && !weakReference.get().isDisposed()){
                compositeDisposable.remove(weakReference.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestMap.clear();
    }

    @Override
    public void success(String message) {

    }

    @Override
    public void error(String message) {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void showWait(String message) {

    }

    @Override
    public void hideWait(WaitScreen.OnAnimationEnd onAnimationEnd) {

    }

    @Override
    public void closeWait() {

    }


    @Override
    public YbAppPreference getYbAppPreference() {
        return ybAppPreference;
    }

    /**
     * corecomponent无用，不用注入
     * @param coreComponent
     */
    @Override
    protected void injectDagger(CoreComponent coreComponent) {
        //TODO
    }

    /**
     * 注解基于ybbasiccomponent的环境
     * @param basicComponent
     */
    protected abstract void injectDagger2(YbBasicComponent basicComponent);
}
