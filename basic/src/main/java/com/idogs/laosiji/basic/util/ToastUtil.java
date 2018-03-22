package com.idogs.laosiji.basic.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * toast 工具类 on 2017/10/18.
 */

public class ToastUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable runnable = () -> mToast.cancel();

    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 时长
     */
    public static void showToast(Context context, String text, int duration) {
        mHandler.removeCallbacks(runnable);
        if (mToast != null) {
            mToast.setText(text);   // 当前token正在显示，直接修改显示的文本
            mToast.setDuration(duration);
        } else {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            //            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        mHandler.postDelayed(runnable, duration);
        mToast.show();
    }

    // 默认duration为1000
    public static void ToastMsg(Context context, String string) {
        ToastMsg(context, string,1000);
    }

    public static void ToastMsg(Context context, String string, int duration) {
        showToast(context, string, duration);
    }

}
