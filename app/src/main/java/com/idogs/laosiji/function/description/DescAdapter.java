package com.idogs.laosiji.function.description;

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

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> mlist;

    public DescAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.mlist=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_desc, parent, false));
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_descitem.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_descitem;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_descitem=itemView.findViewById(R.id.tv_desitem);
        }
    }

}
