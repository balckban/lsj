package com.idogs.laosiji.core.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.idogs.laosiji.core.R;
import com.idogs.laosiji.core.component.CoreComponent;
import com.idogs.laosiji.core.core.ActivityTaskManager;
import com.idogs.laosiji.core.core.UnrealCore;
import com.idogs.laosiji.widget.window.WaitScreen;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


import java.util.Stack;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * <b>类名称：</b> BaseActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 17:26<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    @Inject
    public P presenter;

    private Unbinder unbinder;
    protected ImageView fragment_back;

    Stack<WaitScreen> waitScreens = new Stack<>();

    Handler handler = new Handler();

    public CompositeDisposable compositeDisposable;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        ImmersionBar.with(this).init(); //初始化，默认透明状态栏和黑色导航栏
        compositeDisposable = new CompositeDisposable();
        if (isCustomerView()) {
            setContentView(bindLayout());
        }
        unbinder = ButterKnife.bind(this);
        injectDagger(UnrealCore.getCoreComponent());
        if (presenter != null) {
            presenter.bindLifeCycle(
                    RxLifecycleAndroid.bindActivity(lifecycle()));
        }
        ActivityTaskManager.getInstance().pushActivity(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        afterViews();
    }

    protected abstract void injectDagger(CoreComponent coreComponent);

    @Override
    public void showWait() {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
        waitScreen.show();
        handler.postDelayed(waitScreen::show, 200);
    }

    @Override
    public void showWait(String message) {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
        waitScreen.show(message);
        handler.postDelayed(() -> waitScreen.show(message), 200);
    }

    @Override
    public void hideWait(WaitScreen.OnAnimationEnd onAnimationEnd) {
        WaitScreen waitScreen = waitScreens.pop();
        handler.postDelayed(() -> waitScreen.close(onAnimationEnd), 100);
    }

    @Override
    public void closeWait() {
        WaitScreen waitScreen = waitScreens.pop();
        handler.postDelayed(waitScreen::dismiss, 100);
    }

    @Override
    public void toast(String message) {
        ToastUtils.showLongSafe(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
//        unbinder.unbind();
        while (!waitScreens.empty()) {
            WaitScreen waitScreen = waitScreens.pop();
            waitScreen.dismiss();
        }
        ActivityTaskManager.getInstance().removeActivity(this);
        //释放所有的dispose
        compositeDisposable.dispose();
    }

    @Override
    public final void finishAll() {
        ActivityTaskManager.getInstance().finshAllActivities();
    }

    @SafeVarargs
    @Override
    public final void finish(Class<? extends Activity>... activityClasses) {
        ActivityTaskManager.getInstance().finshActivities(activityClasses);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public boolean isCustomerView() {
        return true;
    }

    public abstract int bindLayout();

    public abstract void afterViews();
}
