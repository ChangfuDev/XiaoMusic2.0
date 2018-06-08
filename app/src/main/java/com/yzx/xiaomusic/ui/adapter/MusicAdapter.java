package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.MusicInfo;

public class MusicAdapter extends CommonBaseAdapter<MusicAdapter.Holder, MusicInfo> {

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new Holder(ResourceUtils.parseLayout(context, R.layout.item_music,parent));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
