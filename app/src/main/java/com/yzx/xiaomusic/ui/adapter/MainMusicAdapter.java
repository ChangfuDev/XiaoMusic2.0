package com.yzx.xiaomusic.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by yzx on 2018/6/15.
 * Description
 */
public class MainMusicAdapter extends CommonBaseAdapter<MainMusicAdapter.Holder, String> {

    private ArrayList<String> titles;
    private ArrayList<Integer> icons;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_main_music));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvTitle.setText(titles.get(position));
        holder.ivCover.setImageResource(icons.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void setData(ArrayList<String> titles, ArrayList<Integer> icons) {
        this.titles = titles;
        this.icons = icons;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
