package com.idogs.laosiji.function.main.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;
import com.idogs.laosiji.function.main.MainActivity;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2017/10/17.
 */

public class tvFragment extends YbAbstractFragment<MainContract.Presenter> implements  MainContract.View,OnRefreshLoadmoreListener {

    @BindView(R.id.tvRecyclerView)
    RecyclerView tv_recyclerView;
    @BindView(R.id.tv_refreshLayout)
    SmartRefreshLayout tv_refreshLayout;  //下拉刷新
    String type ="国产剧,香港剧,韩国剧,欧美剧,日本剧";

    MainContract.Presenter mPresenter;

    public static tvFragment newInstance() {
        return new tvFragment();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_tv;
    }

    @Override
    protected void afterViews() {
        mPresenter=((MainActivity)getActivity()).getmPresenter();
        tv_refreshLayout.setOnRefreshLoadmoreListener(this);
        mPresenter.initTvRecyclerview(type,0,6,tv_recyclerView);

    }




    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        tv_refreshLayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.tv_refresh(refreshlayout);
    }
}
