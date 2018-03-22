package com.idogs.laosiji.function.play;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.idogs.laosiji.R;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.basic.ext.YbAbstractActivity;
import com.idogs.laosiji.basic.preference.YbAppPreference;
import com.idogs.laosiji.config.RouterConfig;
import com.idogs.laosiji.function.play.componet.DaggerPlayComponet;
import com.idogs.laosiji.function.play.contract.PlayContract;
import com.idogs.laosiji.function.play.module.PlayModule;
import static com.idogs.laosiji.R.id.video_fullView;
import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

@Route(path = RouterConfig.MAIN_PLAY)
public class PlayActivity extends YbAbstractActivity<PlayContract.Presenter> implements PlayContract.View  {

    @Inject
    PlayContract.Presenter mPresenter;
    @BindView(R.id.playWeb)
    WebView webView;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    // 全屏时视频加载view
    @BindView(video_fullView)
    FrameLayout videoFullView;
    /**
     * 视频全屏参数
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;

    @Override
    protected void injectDagger2(YbBasicComponent basicComponent) {
        DaggerPlayComponet.builder()
                .ybBasicComponent(basicComponent)
                .playModule(new PlayModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_play;
    }

    @Override
    public void afterViews() {
        SPUtils spUtils=new SPUtils("idogs");
        YbAppPreference idogsPreference=new YbAppPreference(spUtils);
        String access_tonken=idogsPreference.getTokenKey();
        Intent intent =getIntent();
        String url = intent.getStringExtra("movieUrl");
        String playUrl="http://139.199.86.67:8086/movie/playMovie?access_token="+access_tonken+"&mivieSrc="+url;
        initWebView(playUrl);

    }


    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    /**
     * 展示网页界面
     **/
    public void initWebView(String url) {
        WebChromeClient wvcc = new WebChromeClient();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 不加载缓存内容

        webView.setWebChromeClient(wvcc);
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        };
        webView.setWebViewClient(wvc);
        final ProgressDialog dialog = new ProgressDialog(PlayActivity.this);
        dialog.setMessage("正在加载");
        dialog.show();
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    dialog.dismiss();
                }
            }

            /*** 视频播放相关的方法 **/



            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(PlayActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
            }
        });

        // 加载Web地址
        webView.loadUrl(url);
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        PlayActivity.this.getWindow().getDecorView();
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(PlayActivity.this);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
                if (customView != null) {
                    hideCustomView();
                } else if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}
