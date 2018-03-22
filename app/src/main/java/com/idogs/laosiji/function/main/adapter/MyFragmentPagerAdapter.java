package com.idogs.laosiji.function.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.idogs.laosiji.function.main.fragment.ctFragment;
import com.idogs.laosiji.function.main.fragment.movieFragment;
import com.idogs.laosiji.function.main.fragment.recFragment;
import com.idogs.laosiji.function.main.fragment.tvFragment;
import com.idogs.laosiji.function.main.fragment.zyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 5;
    private String[] tableTitle = new String[] {"推荐", "电视剧", "电影","综艺","动漫"};
    private Context mContext;
    private List<Fragment> mFragmentTab;


    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        initFragmentTab();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentTab.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitle[position];
    }

    private void initFragmentTab() {

        mFragmentTab = new ArrayList<Fragment>();
        mFragmentTab.add(recFragment.newInstance());
        mFragmentTab.add(tvFragment.newInstance());
        mFragmentTab.add(movieFragment.newInstance());
        mFragmentTab.add(zyFragment.newInstance());
        mFragmentTab.add(ctFragment.newInstance());
    }


}
