package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.SquareImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 歌单列表Adapter
 */
public class SongSheetListAdapter extends CommonBaseAdapter<SongSheetListAdapter.Holder, SongSheetList.PlaylistsBean> {

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_song_sheet_list, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        Context context = holder.itemView.getContext();
        SongSheetList.PlaylistsBean playlistsBean = datas.get(position);
        GlideUtils.loadImg(context, playlistsBean.getCoverImgUrl(), GlideUtils.TYPE_SONG_SHEET, holder.ivCover);
        int playCount = playlistsBean.getPlayCount();
        holder.tvPlayCount.setText(playCount > 10000 ? String.format("%s万", String.valueOf(playCount / 10000)) : String.valueOf(playCount));
        holder.tvSongSheetDes.setText(playlistsBean.getName().trim());
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        SquareImageView ivCover;
        @BindView(R.id.tv_play_count)
        TextView tvPlayCount;
        @BindView(R.id.tv_songSheetDes)
        TextView tvSongSheetDes;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
