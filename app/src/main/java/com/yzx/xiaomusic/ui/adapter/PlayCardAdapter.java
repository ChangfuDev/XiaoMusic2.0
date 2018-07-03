package com.yzx.xiaomusic.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 播放页面华东的卡片
 */
public class PlayCardAdapter extends CommonBaseAdapter<PlayCardAdapter.Holder, MusicInfo> {


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_play_card, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        MusicInfo musicInfo = datas.get(position);
        GlideUtils.loadImg(holder.itemView.getContext(), musicInfo.getAlbumCoverPath(), GlideUtils.TYPE_PLAY_CARD, holder.ivCover, false);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        CircleImageView ivCover;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
