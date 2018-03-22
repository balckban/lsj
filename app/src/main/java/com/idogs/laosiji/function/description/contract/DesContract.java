package com.idogs.laosiji.function.description.contract;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.idogs.laosiji.basic.ext.YbView;
import com.idogs.laosiji.core.base.BasePresenter;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.idogs.laosiji.widget.view.FullScreenVideoView;

import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public interface DesContract {

    interface View extends YbView {

    }
    interface Presenter extends BasePresenter {
        void getMovieInfo(String id);

        void selMovieDate(Context context,ArrayList<String> date, FamiliarRecyclerView recyclerView);
    }

}
