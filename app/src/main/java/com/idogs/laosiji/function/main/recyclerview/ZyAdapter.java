package com.idogs.laosiji.function.main.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idogs.laosiji.R;
import com.idogs.laosiji.http.model.IdogMovieInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class ZyAdapter extends RecyclerView.Adapter<ZyAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<IdogMovieInfo> mlist;

    public ZyAdapter(Context context, ArrayList<IdogMovieInfo> list) {
        this.mContext = context;
        this.mlist=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item.setText(mlist.get(position).movieName);
        Glide.with(mContext).load(mlist.get(position).movieImgSrc).placeholder(R.mipmap.item).into(holder.iv_item);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_item;
        private TextView tv_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_item=itemView.findViewById(R.id.iv_item);
            tv_item=itemView.findViewById(R.id.tv_item);
        }
    }
    // 更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<IdogMovieInfo> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null) {
            mlist.addAll(newDatas);
        }
        notifyDataSetChanged();
        
    }
}
