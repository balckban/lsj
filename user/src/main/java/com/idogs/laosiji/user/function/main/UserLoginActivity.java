package com.idogs.laosiji.user.function.main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.util.CommonDialogUtil;
import com.idogs.laosiji.basic.util.MD5Utils;
import com.idogs.laosiji.basic.util.ToastUtil;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.user.R;
import com.idogs.laosiji.user.R2;
import com.idogs.laosiji.user.function.main.componet.DaggerUserComponet;
import com.idogs.laosiji.user.function.main.contract.UserContract;
import com.idogs.laosiji.user.function.main.module.UserModule;
import com.idogs.laosiji.user.function.main.widgets.PasswordEditText;
import com.idogs.laosiji.widget.view.FullScreenVideoView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@Route(path = RouterConfig.USER_LOGIN)
public class UserLoginActivity  extends YbAbstractActivity<UserContract.Presenter> implements UserContract.View {

     @BindView(R2.id.videoView)
     FullScreenVideoView mVideoView;
      @BindView(R2.id.login_user)
    EditText login_user;
    @BindView(R2.id.login_pass)
    PasswordEditText login_pass;
    @BindView(R2.id.btn_login)
    Button btn_login;


    @Inject
    UserContract.Presenter mPresenter;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;



    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
        DaggerUserComponet.builder()
                .ybBasicComponent(basicComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void afterViews()
    {
        mPresenter.playVideoView(mVideoView, UserLoginActivity.this);
    }


    @OnClick(R2.id.btn_changetosignupmode)
    public void  changetoSign(){
        ARouter.getInstance().build(RouterConfig.USER_SIGN).navigation();
    }
    @OnClick(R2.id.btn_login)
    public void Login(){
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        CommonDialogUtil.showLoading(this);
        mPresenter.Login(login_user.getText().toString().replace(" ", ""), MD5Utils.md5Password(login_pass.getText().toString().replace(" ", "")));

    }
    //返回重启加载
    @Override
    protected void onRestart() {
        mPresenter.playVideoView(mVideoView,UserLoginActivity.this);
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        mVideoView.stopPlayback();
        super.onStop();
    }


   //返回键两次退出
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this,"再按一次退出程序", Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}
