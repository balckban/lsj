package com.idogs.laosiji.function.chat.fragment;

import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;

/**
 * Created by Administrator on 2017/10/17.
 */

public class chatFragment extends YbAbstractFragment {

    public static chatFragment newInstance() {
        return new chatFragment();
    }


    @Override
    protected int bindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void afterViews() {

    }



    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {

    }



}
