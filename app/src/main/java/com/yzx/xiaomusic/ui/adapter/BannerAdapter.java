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

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yzx on 2018/6/30.
 * Description 推荐轮播图
 */
public class BannerAdapter extends CommonBaseAdapter<BannerAdapter.Holder, String> {

    private final Random random;

    public BannerAdapter() {
        random = new Random();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_banner, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        int bg;
        int textBg;
        holder.tvTitle.setText(String.valueOf(position % 5));
        switch (position % 5) {
            case 0:
                bg = 0xffceceff;
                textBg = 0xffFF4500;
                break;
            case 1:
                bg = 0xffFFB6C1;
                textBg = 0xffFFA500;
                break;
            case 2:
                bg = 0xff6495ED;
                textBg = 0xff5F9EA0;
                break;
            case 3:
                bg = 0xffFA8072;
                textBg = 0xffDA70D6;
                break;
            default:
                bg = 0xffFAEBD7;
                textBg = 0xff2E8B57;
                break;
        }
        holder.tvTitle.setBackgroundColor(textBg);
        holder.ivCover.setBackgroundColor(bg);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
