package com.idogs.laosiji.basic.util;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.laosiji.config.RouterConfig;
import com.jiangyy.easydialog.CommonDialog;
import com.jiangyy.easydialog.LoadingDialog;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class CommonDialogUtil {
     static LoadingDialog.Builder mLoadingBuiler;
     public static void showLoading(Activity context){
         final LoadingDialog.Builder mBuilder = new LoadingDialog.Builder(context);
         mLoadingBuiler=mBuilder;
         mBuilder.setTitle("正在登录ing...");
         mBuilder.showProgress(true).show();
     }
     public static void disLoading(){
         mLoadingBuiler.dismiss();
     }

    public static void showCommon(Activity context){
        new CommonDialog.Builder(context)
                .setTitle("注册成功")
                .setMessage("欢迎您加入老司机队列")
                .setPositiveButton("确定", new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                ARouter.getInstance().build(RouterConfig.USER_LOGIN).navigation();
            }
          }).show();
    }
}


