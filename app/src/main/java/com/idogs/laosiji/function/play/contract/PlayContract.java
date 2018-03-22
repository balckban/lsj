package com.idogs.laosiji.function.play.contract;

import android.content.Context;

import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.core.base.BasePresenter;

import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public interface PlayContract {

    interface View extends YbView {

    }
    interface Presenter extends BasePresenter {
        void  PlayVideo(String url);
    }

}
