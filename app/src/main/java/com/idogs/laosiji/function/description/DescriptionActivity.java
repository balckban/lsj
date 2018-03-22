package com.idogs.laosiji.function.description;

import android.content.Intent;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.RxBus;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.view.ExpandTextView;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.description.componet.DaggerDesComponet;
import com.idogs.laosiji.function.description.contract.DesContract;
import com.idogs.laosiji.function.events.AllEvents;
import com.idogs.laosiji.function.description.module.DesModule;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@Route(path= RouterConfig.DES_MOVIE)
public class DescriptionActivity extends YbAbstractActivity<DesContract.Presenter> implements DesContract.View,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_movie_img)
    ImageView iv_movie_img; //电影图片
    @BindView(R.id.tv_movie_name)
    TextView tv_movie_name; //电影名字
    @BindView(R.id.tv_movie_other_name)
    TextView tv_movie_other_name; //别名
    @BindView(R.id.tv_movie_score)
    TextView tv_movie_score; //观众评分
    @BindView(R.id.tv_movie_type)
    TextView tv_movie_type;//电影类型
    @BindView(R.id.tv_src_dur)
    TextView tv_src_dur;//上映地区
    @BindView(R.id.tv_pubDesc)
    TextView tv_pubDesc;//上映时间
    @BindView(R.id.tv_snum)
    TextView tv_snum;//语种
     @BindView(R.id.expandText_movie_Desc)
    ExpandTextView expandText_movie_Desc;
     @BindView(R.id.rv_movie_date)
    FamiliarRecyclerView rv_movie_date;//电影集数
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;


    @Inject
    DesContract.Presenter mPresenter;
    IdogMovieInfo idogMovieInfo;//电影信息
    String movieId; //接收到的电影ID
    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
         DaggerDesComponet.builder()
                 .ybBasicComponent(basicComponent)
                 .desModule(new DesModule(this))
                 .build()
                 .inject(this);
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_movie_detail;
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }

    @Override
    public void afterViews() {
        swipe.setOnRefreshListener(this);
        Intent intent =getIntent();
        movieId=intent.getStringExtra("movieId");
        mPresenter.getMovieInfo(movieId);
        RxBus.getInstance().toFlowable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (((AllEvents) o).getMovieInfo() != null) {
                    idogMovieInfo=((AllEvents) o).getMovieInfo();
                    setView();
                }
            }
        });
        rv_movie_date.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                ARouter.getInstance().build(RouterConfig.MAIN_PLAY).withString("movieUrl",idogMovieInfo.movieSource.get(position)).navigation();
            }
        });
    }
    //设置界面视图
    public void setView(){
        Glide.with(getApplicationContext()).load(idogMovieInfo.movieImgSrc).placeholder(R.mipmap.item).into(iv_movie_img);
        tv_movie_name.setText(idogMovieInfo.movieName);
        tv_movie_other_name.setText(idogMovieInfo.movieOtherName);
        tv_movie_score.setText(idogMovieInfo.movieGrade);
        tv_movie_type.setText(idogMovieInfo.movieType);
        tv_src_dur.setText(idogMovieInfo.movieArea);
        tv_pubDesc.setText(idogMovieInfo.movieReleaseDate);
        tv_snum.setText(idogMovieInfo.movieLanguage);
        expandText_movie_Desc.setText(idogMovieInfo.movieIntroduction);
        mPresenter.selMovieDate(this,idogMovieInfo.movieSource,rv_movie_date);
    }




    @Override
    public void onRefresh() {
        setView();
        swipe.setRefreshing(false);
    }
}
