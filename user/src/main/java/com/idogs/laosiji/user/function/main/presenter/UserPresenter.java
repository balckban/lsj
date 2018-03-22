package com.idogs.laosiji.user.function.main.presenter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.basic.ext.YbPresenterImpl;
import com.idogs.laosiji.basic.http.covert.YbLocalResponseTransformer;
import com.idogs.laosiji.basic.http.token.YbToken;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.basic.util.CommonDialogUtil;
import com.idogs.laosiji.basic.util.ToastUtil;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.user.R;
import com.idogs.laosiji.user.function.main.componet.DaggerUserComponet;
import com.idogs.laosiji.user.function.main.contract.UserContract;
import com.idogs.laosiji.user.http.component.DaggerUserHttpComponent;
import com.idogs.laosiji.user.http.module.UserHttpModule;
import com.idogs.laosiji.user.http.server.UserServer;
import com.idogs.laosiji.user.http.vo.YbLoginResponse;
import com.idogs.laosiji.user.http.vo.YbRegisterResponse;
import com.idogs.laosiji.widget.view.FullScreenVideoView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class UserPresenter extends YbPresenterImpl<UserContract.View> implements UserContract.Presenter {

    @Inject
    UserServer userServer;

    AppCompatActivity mActivity;
    Context mContext;
    public UserPresenter(UserContract.View view) {
        super(view);
    }


    @Override
    public void injectNetComponet2(YbNetworkComponent component) {
        DaggerUserHttpComponent.builder()
                .ybNetworkComponent(component)
                .userHttpModule(new UserHttpModule())
                .build()
                .inject(this);
    }

    /**
     * 首页背景播放
     */
    @Override
    public void playVideoView(FullScreenVideoView mVideoView,AppCompatActivity activity) {
        mActivity=activity;
        mContext=activity;
        mVideoView.setVideoURI(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.mqr));
        //播放
        mVideoView.start();
        //循环播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });
    }



    /**
     * 登录请求
     */
    @Override
    public void Login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            Toast.makeText(mContext,"用户名密码不能为空",Toast.LENGTH_SHORT).show();
        }
        SPUtils spUtils=new SPUtils("idogs");
        YbAppPreference ybAppPreference=new YbAppPreference(spUtils);
        userServer.Login(username,password,"password")
                .compose(new YbLocalResponseTransformer<YbLoginResponse,List<String>>())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("Login", disposable);
                })
                .subscribe(list->{
                    String access_token= list.get(0);
                    String refreshtoken= list.get(1);
                    String tokentime= list.get(2);
                    ybAppPreference.put("access_token",access_token);
                    CommonDialogUtil.disLoading();
                    ARouter.getInstance().build(RouterConfig.MAIN_ACTIVITY).navigation(mContext, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            mActivity.finish();
                        }
                    });
                },throwable -> {
                    Toast.makeText(mContext,"用户名密码错误",Toast.LENGTH_SHORT).show();
                    view.cancelRequest("Login");
                    CommonDialogUtil.disLoading();

                });

    }

    /**
     * 注册账户
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password, Activity context) {
        userServer.Register(username,password)
                .compose(new YbLocalResponseTransformer<YbRegisterResponse, List<String>>())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("register", disposable);
                })
                .subscribe(list->{
                    String code= list.get(0);
                    String msg= list.get(1);
                    if (msg.equals("Success")){
                      CommonDialogUtil.showCommon(context);
                    }else {
                        Toast.makeText(context,"用户名已被注册",Toast.LENGTH_SHORT).show();
                    }
                },throwable -> {
                    view.cancelRequest("register");
                });
    }
}

