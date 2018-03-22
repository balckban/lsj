package com.idogs.laosiji.function.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;


import com.blankj.utilcode.util.SPUtils;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbNetworkComponent;
import com.idogs.laosiji.basic.ext.RxBus;
import com.idogs.laosiji.basic.ext.YbPresenterImpl;
import com.idogs.laosiji.basic.http.covert.YbLocalResponseTransformer;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.basic.util.GlideImageLoader;
import com.idogs.laosiji.function.chanal.fragment.chanalFragment;
import com.idogs.laosiji.function.chat.fragment.chatFragment;
import com.idogs.laosiji.function.events.AllEvents;
import com.idogs.laosiji.function.main.contract.MainContract;
import com.idogs.laosiji.function.main.fragment.MainFragment;
import com.idogs.laosiji.function.main.fragment.MyFragment;
import com.idogs.laosiji.function.main.recyclerview.CtAdapter;
import com.idogs.laosiji.function.main.recyclerview.MovieSection;
import com.idogs.laosiji.function.main.recyclerview.RectAdapter;
import com.idogs.laosiji.function.main.recyclerview.ZyAdapter;
import com.idogs.laosiji.function.main.recyclerview.tvSection;
import com.idogs.laosiji.http.component.DaggerIdogsComponent;
import com.idogs.laosiji.http.model.IdogHeader;
import com.idogs.laosiji.http.model.IdogMovieInfo;
import com.idogs.laosiji.http.model.IdogsHome;
import com.idogs.laosiji.http.module.IdogsRetrofitModule;
import com.idogs.laosiji.http.server.IdogServer;
import com.idogs.laosiji.http.vo.YbFragmentResponse;
import com.idogs.laosiji.http.vo.YbRecResponse;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;


/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class MainAppPresenterImpl extends YbPresenterImpl<MainContract.View> implements MainContract.Presenter{

    @Inject
    IdogServer idogServer;
    private AppCompatActivity mcontext; //上下文
    public static final String RADIOGROUP_MAIN = "RADIOGROUP_MAIN";   //首页
    public static final String RADIOGROUP_CHANAL = "RADIOGROUP_CHANAL";//频道
    public static final String RADIOGROUP_MSG = "RADIOGROUP_MSG";//社区
    public static final String RADIOGROUP_MINE = "RADIOGROUP_MINE";//我的
    public static final String RADIOGROUP_DOWN = "RADIOGROUP_DOWN";//下载列表
    private Fragment mLastFragment; //所选的一个fragment tag
    private List<String> images;
    private List<String> titles;
//    public MainAppPresenterImpl(MainContract.View view) {
//        super(view);
//    }

    public MainAppPresenterImpl(MainContract.View view,AppCompatActivity context) {
        super(view);
        mcontext=context;
        changeFragment(RADIOGROUP_MAIN);
    }
    SPUtils spUtils=new SPUtils("idogs");
    YbAppPreference idogsPreference=new YbAppPreference(spUtils);
    String access_tonken=idogsPreference.getTokenKey();
    /**---------------------------------- MainActivity代码-------------------------------------------------------*/
    /**
     * 切换Fragment
     */
    @Override
    public void changeFragment(String TAG) {
        //创建管理
        FragmentManager fragmentManager = mcontext.getSupportFragmentManager();
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentByTag == null) {
            fragmentByTag = createFragmentByTag(TAG);
            addFragment(fragmentByTag, TAG);
        } else {
            showFragment(fragmentByTag);
        }
        mLastFragment = fragmentByTag;
    }




    /**
     * 添加fragment
     * @param fragmentByTag
     * @param tag
     */
    private void addFragment(Fragment fragmentByTag, String tag) {
        //创建事务
        FragmentTransaction fragmentTransaction = mcontext.getSupportFragmentManager().beginTransaction();

        if (mLastFragment != null) {
            fragmentTransaction.hide(mLastFragment);
        }

        fragmentTransaction.add(R.id.content_layout, fragmentByTag, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示fragment
     * @param fragmentByTag
     */
    private void showFragment(Fragment fragmentByTag) {

        FragmentTransaction fragmentTransaction = mcontext.getSupportFragmentManager().beginTransaction();

        if (mLastFragment != null) {
            fragmentTransaction.hide(mLastFragment);
        }
        fragmentTransaction.show(fragmentByTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 通过标签创建fragment
     * @param tag
     * @return
     */
    private Fragment createFragmentByTag(String tag) {
        //默认返回首页
        if (TextUtils.isEmpty(tag)&&"null".equals(tag)) {
            return MainFragment.newInstance();
        }

        if (RADIOGROUP_MAIN.equals(tag)) {
            return MainFragment.newInstance();
        }
        if (RADIOGROUP_CHANAL.equals(tag)){
            return chanalFragment.newInstance();
        }
        if (RADIOGROUP_MSG.equals(tag)){
            return chatFragment.newInstance();
        }
        if (RADIOGROUP_MINE.equals(tag)) {
            return MyFragment.newInstance();
        }

       return MainFragment.newInstance();    //返回fragment
    }

    /**------------------------------------------------RecFrament代码------------------------------* **/
//    StaggeredGridLayoutManager layoutManager;
    ArrayList<IdogMovieInfo> list ;
    ArrayList<IdogHeader> headlist;
    RectAdapter rectAdapter;
//    private HeaderFooterAdapter headerFooterAdapter;
    Banner mBanner;
    FamiliarRecyclerView mrecyclerView;
    YbAppPreference ybAppPreference;
    /**
     *Rec数据请求
     * @param
     */
    @Override
    public void getRecList(Integer page, Integer size,FamiliarRecyclerView recyclerView, YbAppPreference ybAppPreference) {
        this.ybAppPreference=ybAppPreference;
        idogServer.getlist(page,size,access_tonken)
               .compose(new YbLocalResponseTransformer<YbRecResponse, IdogsHome>())
               .doOnSubscribe(disposable -> {
                   view.setRequestTag("getRecList", disposable);
               })
               .subscribe(data->{
                   list=data.movieinfo;
                   headlist=data.head;
                   initRecycleView(recyclerView);
                   RxBus.getInstance().send(new AllEvents(list));
                   },throwable -> {
                   view.cancelRequest("getRecList");
                       });

    }



    /**
     * 加载头部数据
     */
    ArrayList<String> images_banner;
    ArrayList<String> titles_banner;
    public void dateBanner(){
        images_banner=new ArrayList<>();
        titles_banner=new ArrayList<>();
        for (int i=0;i<headlist.size();i++){
            images_banner.add(headlist.get(i).infoImgUrl);
            titles_banner.add(headlist.get(i).infoName);
        }

    }




    /**
     * recycleview设置
     * @param recyclerView
     */
    public void initRecycleView(FamiliarRecyclerView recyclerView) {
        mrecyclerView=recyclerView;
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
        addheader(recyclerView);
        recyclerView.addHeaderView(mBanner);
        recyclerView.addHeaderView(body_head);
        recyclerView.addHeaderView(rec_head);
        rectAdapter = new RectAdapter(mcontext,list);
        recyclerView.setAdapter(rectAdapter);


;
    }
    public void refreshRecycleView(FamiliarRecyclerView recyclerView) {
        mrecyclerView=recyclerView;;
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
        addheader(recyclerView);
        dateBanner();
        rectAdapter = new RectAdapter(mcontext,list);
        recyclerView.setAdapter(rectAdapter);
        mBanner.setImageLoader(new GlideImageLoader())
                .setImages(images_banner)
                .setBannerTitles(titles_banner)
                .start();
    }

    View rec_head;
    View body_head;
    /**
     * 添加头部
     */
    public void addheader(FamiliarRecyclerView recyclerView){
        View header = LayoutInflater.from(mcontext).inflate(R.layout.item_header,recyclerView, false);
        body_head= LayoutInflater.from(mcontext).inflate(R.layout.head_body,recyclerView, false);
        rec_head = LayoutInflater.from(mcontext).inflate(R.layout.videothemelist,recyclerView, false);
        mBanner=header.findViewById(R.id.header);
        dateBanner();
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader())
                .setImages(images_banner)
                .setBannerTitles(titles_banner)
                .start();
    }
    /**
     *下拉刷新
     */

    @Override
    public void SwipRefresh(RefreshLayout refreshLayout) {
        idogServer.getlist(0,12,access_tonken)
                .compose(new YbLocalResponseTransformer<YbRecResponse, IdogsHome>())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getRecList", disposable);
                })
                .subscribe(data->{
                    list=data.movieinfo;
                    headlist=data.head;
                    refreshLayout.finishRefresh();
                    refreshRecycleView(mrecyclerView);
                    RxBus.getInstance().send(new AllEvents(list));
                    page=1;
                },throwable -> {
                    view.cancelRequest("getRecList");
                });
    }

    /**
     * 上拉加载
     * @param refreshLayout
     */
    Integer page=1;
    Integer size=12;
    @Override
    public void LoadMore(RefreshLayout refreshLayout) {
        idogServer.getlist(page,size,access_tonken)
                .compose(new YbLocalResponseTransformer<YbRecResponse, IdogsHome>())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getMoreRecList", disposable);
                })
                .subscribe(data->{
                    list=data.movieinfo;
                    rectAdapter.updateList(list);
                    page=page+1;
                    refreshLayout.finishLoadmore();
                },throwable -> {
                    view.cancelRequest("getMoreRecList");
                });
    }

    /*** --------------------------------------------------综艺加载fragment---------------------------------------------------------------------**/

    FamiliarRecyclerView recyclerView_zy;
    ArrayList<IdogMovieInfo> zyfragmentlist;
    ZyAdapter zyAdapter;
    Integer page_zy=1;
    @Override
    public void zySwipRefresh(RefreshLayout refreshLayout) {
        idogServer.getclasslist("综艺",0,15,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getCtRecList", disposable);

                })
                .subscribe(data->{
                    zyfragmentlist=data;
                    initZyDate(recyclerView_zy);
                    refreshLayout.finishRefresh();
                    page_zy=1;
                },throwable -> {
                    view.cancelRequest("getCtRecList");
                });
    }

    @Override
    public void zyLoadMore(RefreshLayout refreshLayout) {
        idogServer.getclasslist("综艺",page_zy,15,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getZyRecList", disposable);
                })
                .subscribe(data->{
                    zyfragmentlist=data;
                    page_zy=page_zy+1;
                    zyAdapter.updateList(zyfragmentlist);
                    refreshLayout.finishLoadmore();
                },throwable -> {
                    view.cancelRequest("getZyRecList");
                });

    }

    @Override
    public void initZyRecyclerview(String typ, Integer page, Integer size, FamiliarRecyclerView recyclerView) {
        recyclerView_zy=recyclerView;
        idogServer.getclasslist(typ,page,size,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getCtRecList", disposable);
                })
                .subscribe(data->{
                    zyfragmentlist=data;
                    initZyDate(recyclerView);
                },throwable -> {
                    view.cancelRequest("getCtRecList");
                });

    }


    public  void  initZyDate(FamiliarRecyclerView recyclerView){
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
        zyAdapter = new ZyAdapter(mcontext,zyfragmentlist);
        recyclerView.setAdapter(zyAdapter);
    }



    /*** --------------------------------------------------动漫加载fragment---------------------------------------------------------------------**/
    FamiliarRecyclerView recyclerView_ct;
    ArrayList<IdogMovieInfo> ctfragmentlist;
    CtAdapter ctAdapter;
    Integer page_ct=1;
    @Override
    public void ctSwipRefresh(RefreshLayout refreshLayout) {
        idogServer.getclasslist("动漫",0,15,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getCtRecList", disposable);
                })
                .subscribe(data->{
                    ctfragmentlist=data;
                    initCtDate(recyclerView_ct);
                    refreshLayout.finishRefresh();
                    page_ct=1;
                },throwable -> {
                    view.cancelRequest("getCtRecList");
                });
    }

    @Override
    public void ctLoadMore(RefreshLayout refreshLayout) {
        idogServer.getclasslist("动漫",page_ct,15,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getCtRecList", disposable);
                })
                .subscribe(data->{
                    ctfragmentlist=data;
                    page_ct=page_ct+1;
                    ctAdapter.updateList(ctfragmentlist);
                    refreshLayout.finishLoadmore();
                },throwable -> {
                    view.cancelRequest("getCtRecList");
                });

    }

    @Override
    public void initCtRecyclerview(String typ, Integer page, Integer size, FamiliarRecyclerView recyclerView) {
        recyclerView_ct=recyclerView;
        idogServer.getclasslist(typ,page,size,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getCtRecList", disposable);
                })
                .subscribe(data->{
                    ctfragmentlist=data;
                    initCtDate(recyclerView);
                },throwable -> {
                    view.cancelRequest("getCtRecList");
                });

    }

    public  void  initCtDate(FamiliarRecyclerView recyclerView){
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
        ctAdapter = new CtAdapter(mcontext,ctfragmentlist);
        recyclerView.setAdapter(ctAdapter);
    }



    /*** -----------------------------------电视剧加载---------------------------------------------------------------------------------**/

    ArrayList<IdogMovieInfo> tvFragmentList;
    private SectionedRecyclerViewAdapter sectionAdapter;
    RecyclerView tv_recyclerview;
    String type_tv;

    @Override
    public void initTvRecyclerview(String typ, Integer page, Integer size,RecyclerView recyclerView) {
        type_tv=typ;
        tv_recyclerview=recyclerView;
        idogServer.getTvlist(typ,page,size,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getTvRecList", disposable);
                })
                .subscribe(data->{
                    tvFragmentList=data;
                    initTvRecycleview();
                },throwable -> {
                    view.cancelRequest("getTvRecList");
                });
    }

    @Override
    public void tv_refresh(RefreshLayout refreshLayout) {
        initTvRecyclerview(type_tv, 0, 6,tv_recyclerview);
        refreshLayout.finishRefresh();
    }




    public void initTvRecycleview(){
        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new tvSection(mcontext,"国产剧", getList(0,6),sectionAdapter));
        sectionAdapter.addSection(new tvSection(mcontext,"香港剧",  getList(6,12),sectionAdapter));
        sectionAdapter.addSection(new tvSection(mcontext,"韩国剧",  getList(12,18),sectionAdapter));
        sectionAdapter.addSection(new tvSection(mcontext,"欧美剧",  getList(18,24),sectionAdapter));
        sectionAdapter.addSection(new tvSection(mcontext,"日本剧",  getList(24,30),sectionAdapter));
        GridLayoutManager glm = new GridLayoutManager(mcontext, 3);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        tv_recyclerview.setLayoutManager(glm);
        tv_recyclerview.setAdapter(sectionAdapter);
    }

   private List<IdogMovieInfo>  getList(int from,int to){
       List<IdogMovieInfo> list=new ArrayList<>();
       for (int i=from;i<to;i++){
           list.add(tvFragmentList.get(i));
       }
        return list;
   }








    /*** -------------------------------------------------movie数据加载----------------------------------------------------------**/
    ArrayList<IdogMovieInfo> movieFragmentList;
    private SectionedRecyclerViewAdapter movie_sectionAdapter;
    RecyclerView movie_recyclerview;
    String type_movie;
    @Override
    public void initmovieRecyclerview(String typ, Integer page, Integer size, RecyclerView recyclerView) {
        type_movie=typ;
        movie_recyclerview=recyclerView;
        idogServer.getTvlist(typ,page,size,access_tonken)
                .compose(new YbLocalResponseTransformer<YbFragmentResponse, ArrayList<IdogMovieInfo>> ())
                .doOnSubscribe(disposable -> {
                    view.setRequestTag("getMovieRecList", disposable);
                })
                .subscribe(data->{
                    movieFragmentList=data;
                    initMovieRecycleview();
                },throwable -> {
                    view.cancelRequest("getMovieRecList");
                });
    }
    public void initMovieRecycleview( ){
        movie_sectionAdapter = new SectionedRecyclerViewAdapter();
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"动作片", getmovieList(0,6),movie_sectionAdapter));
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"喜剧片",  getmovieList(6,12),movie_sectionAdapter));
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"爱情片",  getmovieList(12,18),movie_sectionAdapter));
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"科幻片",  getmovieList(18,24),movie_sectionAdapter));
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"恐怖片",  getmovieList(24,30),movie_sectionAdapter));
        movie_sectionAdapter.addSection(new MovieSection(mcontext,"剧情片",  getmovieList(30,36),movie_sectionAdapter));
        GridLayoutManager glm = new GridLayoutManager(mcontext, 3);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(movie_sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        movie_recyclerview.setLayoutManager(glm);
        movie_recyclerview.setAdapter(movie_sectionAdapter);
    }

    private List<IdogMovieInfo>  getmovieList(int from,int to){
        List<IdogMovieInfo> list=new ArrayList<>();
        for (int i=from;i<to;i++){
            list.add(movieFragmentList.get(i));
        }
        return list;
    }
    @Override
    public void movie_refresh(RefreshLayout refreshLayout) {
        initmovieRecyclerview(type_movie,0,6,movie_recyclerview);
        refreshLayout.finishRefresh();
    }




/**----------------------------------------------------------------------------------------**/

    @Override
    public void injectNetComponet2(YbNetworkComponent component) {
        DaggerIdogsComponent.builder()
                .ybNetworkComponent(component)
                .idogsRetrofitModule(new IdogsRetrofitModule())
                .build()
                .inject(this);
    }
}
