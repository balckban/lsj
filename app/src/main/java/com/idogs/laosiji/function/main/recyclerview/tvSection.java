package com.idogs.laosiji.function.main.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.idogs.laosiji.R;
import com.idogs.laosiji.http.model.IdogMovieInfo;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class tvSection extends StatelessSection {

    String title;
    List<IdogMovieInfo> list;
    Context mContext;
    SectionedRecyclerViewAdapter mSectionAdapter;

    public tvSection(Context context, String title, List<IdogMovieInfo> list, SectionedRecyclerViewAdapter sectionAdapter) {
        super(new SectionParameters.Builder(R.layout.item)
                .headerResourceId(R.layout.section_ex5_header)
                .build());
        this.mContext=context;
        this.title = title;
        this.list = list;
        this.mSectionAdapter=sectionAdapter;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        String name = list.get(position).movieName;
        String category = list.get(position).movieUpdateTime;

        itemHolder.tvItem.setText(name);
        Glide.with(mContext).load(list.get(position).movieImgSrc).placeholder(R.mipmap.item).into(itemHolder.tv_img);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, String.format("Clicked on position #%s of Section %s",
                        mSectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), title),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
       HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);

        headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, String.format("Clicked on more button from the header of Section %s",
                        title),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final Button btnMore;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle =  view.findViewById(R.id.tvTitle);
            btnMore = view.findViewById(R.id.btnMore);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvItem;
        private ImageView tv_img;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvItem = view.findViewById(R.id.tv_item);
            tv_img=view.findViewById(R.id.iv_item);
        }
    }
}
