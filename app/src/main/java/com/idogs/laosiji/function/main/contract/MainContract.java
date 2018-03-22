package com.idogs.laosiji.function.main.contract;


import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.core.base.BasePresenter;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public interface MainContract {

    interface View extends YbView {

    }

    interface Presenter extends BasePresenter {

        /**
         * Fragment切换
         */
        void changeFragment(String TAG);

//----------------------------推荐
        /**
         * 下拉刷新
         * @param refreshLayout
         */
        void SwipRefresh(RefreshLayout refreshLayout);
        /**
         *
         */
        void LoadMore(RefreshLayout refreshLayout);
        /**
         * rec列表加载
         */
        void getRecList(Integer page, Integer size, FamiliarRecyclerView recyclerView, YbAppPreference ybAppPreference);

 //-----------------------------------------------动漫列表加载

        /**
         * 下拉刷新
         * @param refreshLayout
         */
        void ctSwipRefresh(RefreshLayout refreshLayout);
        /**
         *
         */
        void ctLoadMore(RefreshLayout refreshLayout);

        void initCtRecyclerview(String typ,Integer page,Integer size,FamiliarRecyclerView recyclerView);
        //-----------------------------------------------综艺列表加载
        /**
         * 下拉刷新
         * @param refreshLayout
         */
        void zySwipRefresh(RefreshLayout refreshLayout);
        /**
         *
         */
        void zyLoadMore(RefreshLayout refreshLayout);
        void initZyRecyclerview(String typ,Integer page,Integer size,FamiliarRecyclerView recyclerView);
        //-----------------------------------------------tv列表加载

      void initTvRecyclerview(String typ, Integer page, Integer size, RecyclerView recyclerView);
      void tv_refresh(RefreshLayout refreshLayout);
        //-----------------------------------------------电影列表加载
        void initmovieRecyclerview(String typ, Integer page, Integer size, RecyclerView recyclerView);
        void movie_refresh(RefreshLayout refreshLayout);
    }
}
