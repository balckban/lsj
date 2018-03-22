package com.idogs.laosiji.function.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.main.componet.DaggerMainAppComponet;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.idogs.laosiji.function.main.module.MainAppModule;
import com.idogs.laosiji.function.main.presenter.MainAppPresenterImpl;

import com.idogs.laosiji.pay.PayFacade;
import com.idogs.laosiji.pay.PayResultListener;


import butterknife.BindView;
import javax.inject.Inject;
import butterknife.OnClick;

@Route(path = RouterConfig.MAIN_ACTIVITY)
public class MainActivity extends YbAbstractActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.content_layout)
    RelativeLayout mFrame;
    @BindView(R.id.home_fg_layout)
    LinearLayout mainHome;          //首页
    @BindView(R.id.chanal_fg_layout)
    LinearLayout chanalSel;           //频道
    @BindView(R.id.msg_fg_layout)
    LinearLayout msgSel;      //社区
    @BindView(R.id.person_fg_layout)
    LinearLayout mainMy;            //我的
    @BindView(R.id.down_fg_layout)
    LinearLayout download; //下载

    private PayFacade facade;
    @Inject
    MainContract.Presenter mPresenter;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;



    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void afterViews() {
        SPUtils spUtils=new SPUtils("idogs");
        YbAppPreference ybAppPreference=new YbAppPreference(spUtils);
        if(StringUtils.isEmpty(ybAppPreference.getTokenKey())){
            ARouter.getInstance().build(RouterConfig.USER_LOGIN).navigation(this, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    finish();
                }
            });
        }
        mainHome.setSelected(true);         //默认第一个为选中状态
        initPayFacade();
    }





    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
       DaggerMainAppComponet.builder()
                .ybBasicComponent(basicComponent)
                .mainAppModule(new MainAppModule(this,MainActivity.this))
                .build()
                .inject(this);
    }

    /**
     * 支付方式
     */
    private void initPayFacade() {
        facade = new PayFacade(new PayResultListener() {

            @Override
            public void onPaySuccess() {

            }

            @Override
            public void onPayFailure() {

            }

            @Override
            public void onPayConfirming() {

            }
        });
    }

    @OnClick({R.id.home_fg_layout, R.id.chanal_fg_layout, R.id.msg_fg_layout,R.id.person_fg_layout,R.id.down_fg_layout})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.home_fg_layout:
                mainHome.setSelected(true);
                chanalSel.setSelected(false);
                msgSel.setSelected(false);
                mainMy.setSelected(false);
                download.setSelected(false);
                mPresenter.changeFragment(MainAppPresenterImpl.RADIOGROUP_MAIN);
                break;
            case R.id.chanal_fg_layout:
                mainHome.setSelected(false);
                chanalSel.setSelected(true);
                msgSel.setSelected(false);
                mainMy.setSelected(false);
                download.setSelected(false);
                mPresenter.changeFragment(MainAppPresenterImpl.RADIOGROUP_CHANAL);
                break;
            case R.id.msg_fg_layout:
                mainHome.setSelected(false);
                chanalSel.setSelected(false);
                msgSel.setSelected(true);
                mainMy.setSelected(false);
                download.setSelected(false);
                mPresenter.changeFragment(MainAppPresenterImpl.RADIOGROUP_MSG);
                break;
            case R.id.person_fg_layout:
                mainHome.setSelected(false);
                chanalSel.setSelected(false);
                msgSel.setSelected(false);
                mainMy.setSelected(true);
                download.setSelected(false);
                mPresenter.changeFragment(MainAppPresenterImpl.RADIOGROUP_MINE);
               break;
            case R.id.down_fg_layout:
                mainHome.setSelected(false);
                chanalSel.setSelected(false);
                msgSel.setSelected(false);
                mainMy.setSelected(false);
                download.setSelected(true);
                mPresenter.changeFragment(MainAppPresenterImpl.RADIOGROUP_DOWN);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (facade.isUnionBank()) {
            facade.unionProcessResult(data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public MainContract.Presenter getmPresenter(){
         return mPresenter;
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