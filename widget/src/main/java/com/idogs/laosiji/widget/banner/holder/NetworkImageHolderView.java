package com.idogs.laosiji.widget.banner.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idogs.laosiji.widget.R;


public class NetworkImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {

        Glide.with(context)
                .load(data)
                .placeholder(R.drawable.blank_pic)
                .into(imageView);

    }
}
