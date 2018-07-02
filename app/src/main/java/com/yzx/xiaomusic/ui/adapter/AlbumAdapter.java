package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.album.SingerAlbum;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yzx on 2018/7/2.
 * Description 专辑适配器
 */
public class AlbumAdapter extends CommonBaseAdapter<AlbumAdapter.Holder, SingerAlbum.HotAlbumsBean> {


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_album, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        Context context = holder.itemView.getContext();
        SingerAlbum.HotAlbumsBean hotAlbumsBean = datas.get(position);
        holder.tvTitle.setText(hotAlbumsBean.getName());
        String formatData = TimeUtils.getFormatData(hotAlbumsBean.getPublishTime(), "yyyy.MM.dd");
        holder.tvSubTitle.setText(String.format("%s 歌曲%s", formatData, hotAlbumsBean.getSize()));
        GlideUtils.loadImg(context, hotAlbumsBean.getPicUrl(), holder.ivHead);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull Holder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i("ygl", "onViewDetachedFromWindow: ");
    }
}
