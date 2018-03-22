package com.idogs.laosiji.function.play.presenter;


import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.basic.ext.RxBus;
import com.idogs.laosiji.basic.ext.YbPresenterImpl;
import com.idogs.laosiji.basic.http.covert.YbLocalResponseTransformer;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.function.description.DescAdapter;
import com.idogs.laosiji.function.description.contract.DesContract;
import com.idogs.laosiji.function.events.AllEvents;
import com.idogs.laosiji.function.play.contract.PlayContract;
import com.idogs.laosiji.http.component.DaggerIdogsComponent;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.idogs.laosiji.http.module.IdogsRetrofitModule;
import com.idogs.laosiji.http.server.IdogServer;
import com.idogs.laosiji.http.vo.YbDesResponse;
import com.idogs.laosiji.http.vo.YbPlayResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class PlayPresenter extends YbPresenterImpl<PlayContract.View> implements PlayContract.Presenter {

    @Inject
    IdogServer idogServer;


    public PlayPresenter(PlayContract.View view) {
        super(view);
    }

    @Override
    public void injectNetComponet2(YbNetworkComponent component) {
        DaggerIdogsComponent.builder()
                .ybNetworkComponent(component)
                .idogsRetrofitModule(new IdogsRetrofitModule())
                .build()
                .inject(this);
    }

    @Override
    public void PlayVideo(String url) {

    }
}

