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
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.widget.SquareImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yzx
 * @date 2018/6/21
 * Description 歌单列表Adapter
 */
public class AlbumListAdapter extends CommonBaseAdapter<AlbumListAdapter.Holder, LatestAlbumList.AlbumsBean> {

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_album_list, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        Context context = holder.itemView.getContext();
        LatestAlbumList.AlbumsBean albumsBean = datas.get(position);
        GlideUtils.loadImg(context, albumsBean.getPicUrl(), GlideUtils.TYPE_SONG_SHEET, holder.ivCover);
        holder.tvSongSheetDes.setText(albumsBean.getName());
        holder.tvSubTitle.setText(albumsBean.getArtist().getName());
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        SquareImageView ivCover;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_songSheetDes)
        TextView tvSongSheetDes;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
