package com.idogs.laosiji.function.main.fragment;

import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;
import com.idogs.laosiji.function.main.MainActivity;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by Administrator on 2017/10/17.
 */

public class zyFragment extends YbAbstractFragment<MainContract.Presenter> implements  MainContract.View,OnRefreshLoadmoreListener {

    @BindView(R.id.zyRecyclerView)
    FamiliarRecyclerView zy_recyclerView;
    @BindView(R.id.zy_refreshLayout)
    SmartRefreshLayout zy_refreshLayout;  //下拉刷新

    MainContract.Presenter mPresenter;

    public static zyFragment newInstance() {
        return new zyFragment();
    }
    @Override
    protected void afterViews()
    {
        mPresenter=((MainActivity)getActivity()).getmPresenter();
        mPresenter.initZyRecyclerview("综艺",0,15,zy_recyclerView);
        zy_refreshLayout.setOnRefreshLoadmoreListener(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_zy;
    }




    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.zyLoadMore(zy_refreshLayout);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.zySwipRefresh(zy_refreshLayout);
    }



}
