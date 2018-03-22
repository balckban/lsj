package com.idogs.laosiji.user.function.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.util.MD5Utils;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.user.R;
import com.idogs.laosiji.user.R2;
import com.idogs.laosiji.user.function.main.componet.DaggerUserComponet;
import com.idogs.laosiji.user.function.main.contract.UserContract;
import com.idogs.laosiji.user.function.main.module.UserModule;
import com.idogs.laosiji.user.function.main.widgets.PasswordEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@Route(path = RouterConfig.USER_SIGN)
public  class SignupActivity extends YbAbstractActivity<UserContract.Presenter> implements UserContract.View  {

    @BindView(R2.id.signup_user)
    EditText signup_user;
    @BindView(R2.id.signup_pass)
    PasswordEditText signup_pass;


    @Inject
    UserContract.Presenter mPresenter;


    @Override
    public int bindLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void afterViews() {

    }

    @OnClick(R2.id.btn_changetologinmode)
    public void toLogin(){
        ARouter.getInstance().build(RouterConfig.USER_LOGIN).navigation();
    }

    @OnClick(R2.id.btn_signup)
    public void registerBt(){
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        mPresenter.register(signup_user.getText().toString().replace(" ", ""), MD5Utils.md5Password(signup_pass.getText().toString().replace(" ", "")),this);
    }

    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
        DaggerUserComponet.builder()
                .ybBasicComponent(basicComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }
}
