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
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.ui.dialog.BottomMusicInfoDialog;
import com.yzx.xiaomusic.utils.MusicDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author yzx
 * 音乐适配器
 */
public class MusicAdapter extends CommonBaseAdapter<MusicAdapter.Holder, MusicInfo> {

    private final SupportFragment parentFragment;
    private FragmentManager fragmentManager;
    public static final String KEY_MUSIC_INFO = "musicInfo";

    public MusicAdapter(FragmentManager fragmentManager, SupportFragment parentFragment) {
        this.fragmentManager = fragmentManager;
        this.parentFragment = parentFragment;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new Holder(ResourceUtils.parseLayout(context, R.layout.item_music, parent));
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        MusicInfo musicInfo = datas.get(position);
        holder.tvSort.setText(String.valueOf(position + 1));
        holder.tvTitle.setText(musicInfo.getMusicName());
        if (TextUtils.isEmpty(musicInfo.getAlbumName())) {
            holder.tvSubTitle.setText(String.format("%s", MusicDataUtils.getSingers(musicInfo)));
        } else {
            holder.tvSubTitle.setText(String.format("%s -- %s", MusicDataUtils.getSingers(musicInfo), musicInfo.getAlbumName()));
        }
        holder.ivMv.setVisibility(musicInfo.isLocal() || TextUtils.equals("0", musicInfo.getMvId()) ? View.GONE : View.VISIBLE);
        holder.ivMore.setOnClickListener(v -> {
            BottomMusicInfoDialog bottomMusicInfoDialog = new BottomMusicInfoDialog();
            Bundle args = new Bundle();
            bottomMusicInfoDialog.setParentFragment(parentFragment);
            args.putSerializable(KEY_MUSIC_INFO, musicInfo);
            bottomMusicInfoDialog.setArguments(args);
            bottomMusicInfoDialog.show(fragmentManager, MusicAdapter.class.getSimpleName());
        });
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sort)
        TextView tvSort;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.iv_mv)
        ImageView ivMv;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
