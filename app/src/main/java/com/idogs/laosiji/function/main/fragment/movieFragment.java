package com.idogs.laosiji.function.main.fragment;

import android.support.v7.widget.RecyclerView;

import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;
import com.idogs.laosiji.function.main.MainActivity;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/10/17.
 */

public class movieFragment extends YbAbstractFragment<MainContract.Presenter> implements  MainContract.View,OnRefreshLoadmoreListener {

    @BindView(R.id.movieRecyclerView)
    RecyclerView movie_recyclerView;
    @BindView(R.id.movie_refreshLayout)
    SmartRefreshLayout movie_refreshLayout;  //下拉刷新
    String type =" 动作片,喜剧片,爱情片,科幻片,恐怖片,剧情片";

    MainContract.Presenter mPresenter;


    public static movieFragment newInstance() {
        return new movieFragment();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void afterViews() {
        mPresenter=((MainActivity)getActivity()).getmPresenter();
        mPresenter.initmovieRecyclerview(type,0,6,movie_recyclerView);
        movie_refreshLayout.setOnRefreshLoadmoreListener(this);

    }




    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        movie_refreshLayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
     mPresenter.movie_refresh(movie_refreshLayout);
    }
}
