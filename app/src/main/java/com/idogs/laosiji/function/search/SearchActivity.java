package com.idogs.laosiji.function.search;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ckfree.common.SimpleSearchBar;
import com.czp.searchmlist.mSearchLayout;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.view.SearchEditText;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.description.module.DesModule;
import com.idogs.laosiji.function.search.componet.DaggerSearchComponet;
import com.idogs.laosiji.function.search.contract.SearchContract;
import com.idogs.laosiji.function.search.module.SearchModule;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

@Route(path= RouterConfig.MOVIE_SEARCH)
public class SearchActivity extends YbAbstractActivity<SearchContract.Presenter> implements SearchContract.View {

    @Inject
    SearchContract.Presenter mPresenter;
    @BindView(R.id.back_finish)
    ImageView back;
    @BindView(R.id.query)
    SearchEditText query;
    @BindView(R.id.search_rv)
    RecyclerView search_rv;

    @Override
    public int bindLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void afterViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        query.setOnSearchClickListener(new SearchEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                String content = query.getText().toString().trim();
                if (!TextUtils.isEmpty(content))
                    Toast.makeText(SearchActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
        DaggerSearchComponet.builder()
                .ybBasicComponent(basicComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }
    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                v.clearFocus();
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
