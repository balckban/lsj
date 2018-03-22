package com.idogs.laosiji.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.idogs.laosiji.core.R;
import com.idogs.laosiji.core.component.CoreComponent;
import com.idogs.laosiji.core.core.ActivityTaskManager;
import com.idogs.laosiji.core.core.UnrealCore;
import com.idogs.laosiji.widget.window.WaitScreen;


import java.util.Stack;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * <b>类名称：</b> BaseFragment <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月05日 15:05<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    public P presenter;
    View view;

    protected ImageView fragment_logo;
    protected ImageView fragment_cache;
    protected ImageView fragment_sea;
    protected ImageView fragment_more;
    private BaseActivity baseActivity;
    private Unbinder unbinder;

    Stack<WaitScreen> waitScreens = new Stack<>();

    Handler handler = new Handler();

    public CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDagger(UnrealCore.getCoreComponent());
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    //绑定title
    public void initBarTitleView() {

        fragment_sea =  view.findViewById(R.id.fragment_down_right);
        fragment_logo =  view.findViewById(R.id.fragment_logo);
        fragment_more =  view.findViewById(R.id.fragment_more_right);

//        if (fragment_back != null) {
//            fragment_back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }

//        if (mBarTitle != null) {
//            mBarTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    _mActivity.showFragmentStackHierarchyView();
//
//                }
//            });
//        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBarTitleView();
        afterViews();
    }

    protected abstract void afterViews();

    protected abstract int bindLayout();

    protected abstract void injectDagger(CoreComponent appComponent);


    @Override
    public void showWait() {
        WaitScreen waitScreen = new WaitScreen(getActivity());
        waitScreens.push(waitScreen);
        handler.postDelayed(waitScreen::show, 200);
    }

    @Override
    public void showWait(String message) {
        WaitScreen waitScreen = new WaitScreen(getActivity());
        waitScreens.push(waitScreen);
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
    public void finish() {
        getActivity().finish();
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
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
        while (!waitScreens.empty()) {
            WaitScreen waitScreen = waitScreens.pop();
            waitScreen.dismiss();
        }
        //释放所有的dispose
        compositeDisposable.dispose();
    }
}
