package com.idogs.laosiji.function.chanal.fragment;

import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractFragment;

/**
 * Created by Administrator on 2017/10/17.
 */

public class chanalFragment extends YbAbstractFragment {

    public static chanalFragment newInstance() {
        return new chanalFragment();
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
