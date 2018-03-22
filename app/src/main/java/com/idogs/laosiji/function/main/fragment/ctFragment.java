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

public class ctFragment extends YbAbstractFragment<MainContract.Presenter> implements  MainContract.View,OnRefreshLoadmoreListener {

    @BindView(R.id.ctRecyclerView)
    FamiliarRecyclerView ct_recyclerView;
    @BindView(R.id.ct_refreshLayout)
    SmartRefreshLayout ct_refreshLayout;  //下拉刷新

    MainContract.Presenter mPresenter;

    public static ctFragment newInstance() {
        return new ctFragment();
    }
    @Override
    protected void afterViews() {
        mPresenter=((MainActivity)getActivity()).getmPresenter();
        mPresenter.initCtRecyclerview("动漫",0,15,ct_recyclerView);
        ct_refreshLayout.setOnRefreshLoadmoreListener(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_ct;
    }





    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
         mPresenter.ctLoadMore(ct_refreshLayout);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.ctSwipRefresh(ct_refreshLayout);
    }
}
