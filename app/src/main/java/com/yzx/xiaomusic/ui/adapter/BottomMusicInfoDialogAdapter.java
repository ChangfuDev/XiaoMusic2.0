package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.utils.MusicDataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yzx
 * @date 2018/2/27
 * Description 底部弹出的音乐信息对话框Adapter
 */

public class BottomMusicInfoDialogAdapter extends CommonBaseAdapter<BottomMusicInfoDialogAdapter.Holder, Integer> {

    private final MusicInfo musicInfo;

    public BottomMusicInfoDialogAdapter(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_music_info, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        super.onBindViewHolder(holder, position);
        Context context = holder.itemView.getContext();
        holder.ivIcon.setImageResource(datas.get(position));
        String title;
        switch (position) {
            case 0:
                title = String.format("评论：(%s)", musicInfo.getEvaluteCount());
                break;
            case 1:
                title = ResourceUtils.parseString(context, R.string.share);
                break;
            case 2:
                title = ResourceUtils.parseString(context, R.string.collectToSongSheet);
                break;
            case 3:
                title = String.format("歌手：%s", MusicDataUtils.getSingers(musicInfo));
                break;
            case 4:
                title = String.format("专辑：%s", musicInfo.getAlbumName());
                break;
            case 5:
                title = String.format("音质：%s", "120k/s");
                break;
            case 6:
                title = "查看视频";
                holder.itemView.setClickable(!TextUtils.equals("0", musicInfo.getMvId()));
                holder.itemView.setAlpha(TextUtils.equals("0", musicInfo.getMvId()) ? 0.5f : 1f);
                break;
            case 7:
                title = "相似推荐";
                break;
            case 8:
                title = "定时停止播放";
                break;
            case 9:
                title = "车载模式";
                break;
            default:
                title = String.format("专辑：%s", musicInfo.getAlbumName());
                break;
        }
        holder.tvTitle.setText(title);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
