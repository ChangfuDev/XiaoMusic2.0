package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.utils.MusicDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yzx
 * @date 2018/6/26
 * Description  底部弹框歌单Adapter
 */
public class BottomSongSheetDialogAdapter extends CommonBaseAdapter<BottomSongSheetDialogAdapter.Holder, MusicInfo> {

    private int currentPosition;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_bottom_dialog_song_sheet, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);

        Context context = holder.itemView.getContext();
        MusicInfo musicInfo = datas.get(position);
        holder.tvTitle.setText(musicInfo.getMusicName());
        holder.tvSubTitle.setText(MusicDataUtils.getSingers(musicInfo));
        holder.ivDelete.setOnClickListener(v -> {
            ToastUtils.showToast("从歌单中删除" + musicInfo.getMusicName());
        });
        if (position == currentPosition) {
            holder.ivPlayingFlag.setVisibility(View.VISIBLE);
            holder.tvTitle.setTextColor(ResourceUtils.parseColor(context, R.color.colorAccent));
            holder.tvSubTitle.setTextColor(ResourceUtils.parseColor(context, R.color.colorAccent));
        } else {
            holder.ivPlayingFlag.setVisibility(View.GONE);
            holder.tvTitle.setTextColor(ResourceUtils.parseColor(context, R.color.colorTitle));
            holder.tvSubTitle.setTextColor(ResourceUtils.parseColor(context, R.color.colorSubTitle));
        }
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.iv_playing_flag)
        ImageView ivPlayingFlag;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
