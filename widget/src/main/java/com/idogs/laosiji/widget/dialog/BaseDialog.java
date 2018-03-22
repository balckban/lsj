package com.idogs.laosiji.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idogs.laosiji.widget.R;


/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class BaseDialog {

    static AlertDialog alertDialog;
    private RelativeLayout relativeLayout;
    private TextView tv_title;//标题
    private ImageView img;//图片
    private TextView tv_contant;//内容
    private TextView left_btn;//左边按钮内容
    private TextView right_btn;//右边按钮内容
    public SureInterfance sureListener;
    public ICancelListener cancelListener; //按钮监听
    private View view_yn;

    /**
     * @param context
     * @param wait            是否为等待
     * @param l               是否显示取消按钮
     * @param r               是否显示确定按钮
     * @param c               是否允许点击空白处关闭
     * @param b               是否允许点击消息框关闭
     * @param img             图片
     * @param pic             是否显示图片
     * @param AlertTitle      标题
     * @param tt              是否显示标题
     * @param AlertContent    内容
     * @param LContent        左边按钮内容
     * @param RContent        右边按钮内容
     * @param listener        左边按钮监听
     * @param iCancelListener 右边按钮监听
     * @param dislistener     窗口dismiss监听事件
     */

    public BaseDialog(Context context, boolean wait, boolean l, boolean r, boolean c, boolean b, int img, boolean pic, String AlertTitle
            , boolean tt, String AlertContent, String LContent, String RContent, SureInterfance listener,
                      ICancelListener iCancelListener, DialogInterface.OnDismissListener dislistener) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCanceledOnTouchOutside(c);  //是否允许空白处关闭
        initView(context, img, pic, wait);
        sureListener = listener;
        cancelListener = iCancelListener;
        //设置标题，图片，内容
        tv_title.setText(AlertTitle);
        tv_contant.setText(AlertContent);
        left_btn.setText(LContent);
        //窗口消失监听方法
        setOnDismissListener(dislistener);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.cancelTodo();
                alertDialog.dismiss();
            }
        });
        right_btn.setText(RContent);
        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureListener.sureTodo();
                alertDialog.dismiss();
            }
        });
        //如果两个按钮都不显示 去除下划线
        if (l == false && r == false) {
            view_yn.setVisibility(View.GONE);
        }


        //按钮 标题是否显示
        if (!l) {
            left_btn.setVisibility(View.GONE);
        }
        if (!r) {
            right_btn.setVisibility(View.GONE);
        }
        if (!tt) {
            tv_title.setVisibility(View.GONE);
        }
        if (b) {
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

    }

    /**
     * 初始化dialog界面
     */
    private void initView(Context context, int i, boolean pic, boolean wait) {
        relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_base_dialog, null);
        tv_title = (TextView) relativeLayout.findViewById(R.id.dialogtitle);
        tv_contant = (TextView) relativeLayout.findViewById(R.id.dialogmessage);
        left_btn = (TextView) relativeLayout.findViewById(R.id.dialogno);
        right_btn = (TextView) relativeLayout.findViewById(R.id.dialogyes);
        img = (ImageView) relativeLayout.findViewById(R.id.dialogimg);
        view_yn = relativeLayout.findViewById(R.id.view_yn);
        if (wait) {
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
            // 使用ImageView显示动画
            img.startAnimation(hyperspaceJumpAnimation);
        }

        img.setImageResource(i);

        //图片是否显示
        if (!pic) {
            img.setVisibility(View.GONE);
        }

    }

    /**
     * 显示alertDialog
     */
    public void showCustomDialog() {
        alertDialog.show();
        //设置dialog弹窗
        alertDialog.setContentView(relativeLayout);
        Window window = alertDialog.getWindow();
        ViewGroup.LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        //设置dialog的宽度为 屏幕宽度的 60%
        layoutParams.width = (int) (display.getWidth() * 0.6);
        window.setAttributes((WindowManager.LayoutParams) layoutParams);

    }

    public boolean isShowing() {
        return alertDialog != null && alertDialog.isShowing();
    }

    public void dismiss() {
        if (isShowing())
            alertDialog.dismiss();
    }

    /**
     * 窗口消失监听方法
     */
    private void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        alertDialog.setOnDismissListener(listener);
    }


    /**
     * 按钮监听事件
     */
    public interface SureInterfance {
        void sureTodo();
    }

    public interface ICancelListener {
        void cancelTodo();
    }

}
