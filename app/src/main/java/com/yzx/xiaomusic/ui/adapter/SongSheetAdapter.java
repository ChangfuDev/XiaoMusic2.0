package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yzx
 * 歌单适配器
 */
public class SongSheetAdapter extends CommonBaseAdapter<SongSheetAdapter.SongSheetHolder, SongSheetInfo> {

    @NonNull
    @Override
    public SongSheetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongSheetHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_common_song_sheet, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull SongSheetHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Context context = holder.itemView.getContext();
        SongSheetInfo songSheet = datas.get(position);
        GlideUtils.loadImg(context, songSheet.getCoverUrl(), holder.ivHead);
        holder.tvTitle.setText(songSheet.getTitle());
        holder.tvSubTitle.setText(String.format("%s首，by %s", songSheet.getMusicCount(), songSheet.getCreatorNickName()));
    }

    class SongSheetHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.rl_search_song_sheet)
        RelativeLayout rlSearchSongSheet;

        public SongSheetHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
