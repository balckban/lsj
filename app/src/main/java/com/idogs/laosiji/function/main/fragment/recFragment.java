package com.idogs.laosiji.function.main.fragment;



import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.RxBus;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.events.AllEvents;
import com.idogs.laosiji.function.main.MainActivity;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/17.
 */

public class recFragment extends YbAbstractFragment<MainContract.Presenter> implements  MainContract.View,OnRefreshLoadmoreListener{


    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;  //下拉刷新

    MainContract.Presenter mPresenter;
    ArrayList<IdogMovieInfo> idogMovieInfos;

    public static recFragment newInstance() {
        return new recFragment();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_rec;
    }



    @Override
    protected void afterViews() {
        mPresenter=((MainActivity)getActivity()).getmPresenter();
        SPUtils spUtils=new SPUtils("idogs");
        ybAppPreference=new YbAppPreference(spUtils);

        mPresenter.getRecList(0,12,recyclerView,ybAppPreference);
        refreshLayout.setOnRefreshLoadmoreListener(this);
        RxBus.getInstance().toFlowable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (((AllEvents) o).getListMovieInfo() != null) {
                    idogMovieInfos=((AllEvents) o).getListMovieInfo() ;
                }
            }
        });
        recyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
                @Override
                public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                    ARouter.getInstance().build(RouterConfig.DES_MOVIE).withString("movieId",idogMovieInfos.get(position)._id).navigation();
                }
            });

//        recyclerView.setOnItemLongClickListener(new FamiliarRecyclerView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
//
//                Toast.makeText(getActivity(), "onItemLongClick = " + position, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
    }




    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.SwipRefresh(refreshlayout);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.LoadMore(refreshlayout);
    }
}
