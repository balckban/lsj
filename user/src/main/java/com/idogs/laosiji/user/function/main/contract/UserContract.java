package com.idogs.laosiji.user.function.main.contract;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.core.base.BasePresenter;
import com.idogs.laosiji.widget.view.FullScreenVideoView;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public interface UserContract {

    interface View extends YbView {

    }
    interface Presenter extends BasePresenter {

        void playVideoView(FullScreenVideoView mVideoView,AppCompatActivity activity);

        void Login(String username, String password);

        void  register(String username, String password, Activity context);
    }

}
