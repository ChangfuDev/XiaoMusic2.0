package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.dialog.BottomMusicInfoDialog;
import com.yzx.xiaomusic.utils.JsonUtils;
import com.yzx.xiaomusic.utils.MusicDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * 音乐播放排行适配器
 */
public class RankPlayAdapter extends CommonBaseAdapter<RankPlayAdapter.Holder, ExtraMusicInfo> {

    private final SupportFragment parentFragment;
    private FragmentManager fragmentManager;
    public static final String KEY_MUSIC_INFO = "musicInfo";

    public RankPlayAdapter(FragmentManager fragmentManager, SupportFragment parentFragment) {
        this.fragmentManager = fragmentManager;
        this.parentFragment = parentFragment;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new Holder(ResourceUtils.parseLayout(context, R.layout.item_rank_play, parent));
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        ExtraMusicInfo extraMusicInfo = datas.get(position);
        holder.tvSort.setText(String.valueOf(position + 1));
        holder.tvPlayCount.setText(String.format("%s次", extraMusicInfo.getPlayCount()));
        MusicInfo musicInfo = (MusicInfo) JsonUtils.stringToObject(extraMusicInfo.getMusicInfo(), MusicInfo.class);
        holder.tvTitle.setText(musicInfo.getMusicName());
        if (TextUtils.isEmpty(musicInfo.getAlbumName())) {
            holder.tvSubTitle.setText(String.format("%s", MusicDataUtils.getSingers(musicInfo)));
        } else {
            holder.tvSubTitle.setText(String.format("%s -- %s", MusicDataUtils.getSingers(musicInfo), musicInfo.getAlbumName()));
        }
        holder.ivMore.setOnClickListener(v -> {
            BottomMusicInfoDialog bottomMusicInfoDialog = new BottomMusicInfoDialog();
            Bundle args = new Bundle();
            bottomMusicInfoDialog.setParentFragment(parentFragment);
            args.putSerializable(KEY_MUSIC_INFO, musicInfo);
            bottomMusicInfoDialog.setArguments(args);
            bottomMusicInfoDialog.show(fragmentManager, RankPlayAdapter.class.getSimpleName());
        });
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sort)
        TextView tvSort;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_play_count)
        TextView tvPlayCount;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
