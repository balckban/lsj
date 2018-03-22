package com.idogs.laosiji.function.description.presenter;


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
import com.idogs.laosiji.http.component.DaggerIdogsComponent;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.idogs.laosiji.http.module.IdogsRetrofitModule;
import com.idogs.laosiji.http.server.IdogServer;
import com.idogs.laosiji.http.vo.YbDesResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class DesPresenter extends YbPresenterImpl<DesContract.View> implements DesContract.Presenter {

    @Inject
    IdogServer idogServer;
    DescAdapter descAdapter;

    SPUtils spUtils=new SPUtils("idogs");
    YbAppPreference idogsPreference=new YbAppPreference(spUtils);
    String access_tonken=idogsPreference.getTokenKey();

    public DesPresenter(DesContract.View view) {
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

    IdogMovieInfo idogMovieInfo;
    @Override
    public void getMovieInfo(String id) {
        idogServer.getMovieInfo(id,access_tonken)
                .compose(new YbLocalResponseTransformer<YbDesResponse, IdogMovieInfo>())
                .doOnSubscribe(disposable -> {
                     view.setRequestTag("getMovieInfo", disposable);
              })
                .subscribe(data->{
                    idogMovieInfo=data;
                    RxBus.getInstance().send(new AllEvents(idogMovieInfo));
                },throwable -> {
                    view.cancelRequest("getMovieInfo");
                });

    }

    /**
     * 集数显示
     */
    @Override
    public void selMovieDate(Context context, ArrayList<String> date, FamiliarRecyclerView recyclerView) {
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
        descAdapter=new DescAdapter(context,date);
        recyclerView.setAdapter(descAdapter);
    }


}

