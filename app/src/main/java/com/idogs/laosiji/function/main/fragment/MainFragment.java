package com.idogs.laosiji.function.main.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.main.adapter.MyFragmentPagerAdapter;
import com.idogs.laosiji.widget.banner.listener.OnItemClickListener;

import butterknife.BindView;

/**
 * 首页frgament
 */

public class MainFragment extends YbAbstractFragment implements OnItemClickListener {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;    //指示栏
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private MyFragmentPagerAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void initBarTitleView() {
        super.initBarTitleView();
        fragment_sea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterConfig.MOVIE_SEARCH).navigation();
            }
        });
    }

    @Override
    protected void afterViews() {

        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }






    @Override
    protected int bindLayout() {
        return R.layout.fragment_main;
    }



    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }



    @Override
    public void onItemClick(int position) {

    }
}
