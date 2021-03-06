package com.yzx.xiaomusic.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.yzx.commonlibrary.base.adapter.CommonBaseAdapter;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.leancloud.utils.LeanUtils;
import com.yzx.xiaomusic.Constant;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.db.DBUtils;
import com.yzx.xiaomusic.db.entity.ExtraMusicInfo;
import com.yzx.xiaomusic.model.entity.common.MusicInfo;
import com.yzx.xiaomusic.model.entity.common.SongSheetInfo;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.JsonUtils;
import com.yzx.xiaomusic.widget.UnScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;


/**
 * @author yzx
 * @date 2018/6/15
 * Description
 */
public class MainMusicAdapter extends CommonBaseAdapter<RecyclerView.ViewHolder, String> {

    private final SupportFragment parentFragment;
    private ArrayList<String> titles;
    private ArrayList<Integer> icons;
    public static final int TYPE_SONG_SHEET = 1;

    public MainMusicAdapter(SupportFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public int getItemViewType(int position) {

        if (position > 4) {
            return TYPE_SONG_SHEET;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (TYPE_SONG_SHEET == viewType) {
            return new SongSheetHolder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_main_song_sheet, parent));
        }
        return new Holder(ResourceUtils.parseLayout(parent.getContext(), R.layout.item_main_music, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Context context = holder.itemView.getContext();
        if (position > 4) {
            SongSheetHolder songSheetHolder = (SongSheetHolder) holder;
            SongSheetAdapter adapter = new SongSheetAdapter();

            adapter.setOnItemClickListener((view, position1) -> {
                SongSheetInfo songSheetInfo = adapter.datas.get(position1);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_NAME, songSheetInfo.getTitle());
                bundle.putString(KEY_COVER, songSheetInfo.getCoverUrl());
                bundle.putString(KEY_ID, String.valueOf(songSheetInfo.getId()));
                FragmentStartUtils.startFragment(parentFragment, new SongSheetDetailFragment(), bundle);
            });
            songSheetHolder.ivOpenClose.setOnClickListener(v -> {
                controlVisible(songSheetHolder);
            });

            songSheetHolder.tvTitle.setOnClickListener(v -> {
                controlVisible(songSheetHolder);
            });
            songSheetHolder.recyclerView.setAdapter(adapter);
            if (position == 5) {
                songSheetHolder.tvTitle.setText(String.format("创建的歌单(%s)", "1"));
                ArrayList<SongSheetInfo> songSheetInfos = new ArrayList<>();
                SongSheetInfo songSheetInfo = new SongSheetInfo();
                songSheetInfo.setId("-1");
                //根据登录状态显示用户信息
                if (LeanUtils.isLogin()) {
                    String username = AVUser.getCurrentUser().getUsername();
                    songSheetInfo.setCreatorNickName(username);
                } else {
                    songSheetInfo.setCreatorNickName("");
                }

                songSheetInfo.setTitle("我喜欢的音乐");

                List<ExtraMusicInfo> allLikedMusicInfos = DBUtils.getExtraMusicInfoDao().getAllLikedMusicInfos();
                if (allLikedMusicInfos.size() > 0) {
                    String musicInfoString = allLikedMusicInfos.get(0).getMusicInfo();
                    MusicInfo musicInfo = (MusicInfo) JsonUtils.stringToObject(musicInfoString, MusicInfo.class);
                    songSheetInfo.setCoverUrl(musicInfo.getAlbumCoverPath());
                } else {
                    songSheetInfo.setCoverUrl(Constant.PIC);
                }
                int size = DBUtils.getExtraMusicInfoDao().getAllLikedMusicInfos().size();
                songSheetInfo.setMusicCount(String.valueOf(size));
                songSheetInfos.add(songSheetInfo);
                adapter.setData(songSheetInfos);
            } else if (position == 6) {
                List<SongSheetInfo> allCollectedSongSheet = DBUtils.getCollectedSongSheetDao().getAllCollectedSongSheet();
                songSheetHolder.tvTitle.setText(String.format("收藏的歌单(%s)", allCollectedSongSheet.size()));
                adapter.setData(allCollectedSongSheet);
            }

            songSheetHolder.recyclerView.setLayoutManager(new UnScrollLinearLayoutManager(context));

        } else {
            Holder titleHolder = (Holder) holder;
            titleHolder.tvTitle.setText(titles.get(position));
            titleHolder.ivCover.setImageResource(icons.get(position));
        }
    }

    private void controlVisible(SongSheetHolder songSheetHolder) {
        int visibility = songSheetHolder.recyclerView.getVisibility();
        songSheetHolder.recyclerView.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        songSheetHolder.ivOpenClose.animate().rotation(visibility == View.GONE ? 90 : 0).setDuration(150).start();
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public void setData(ArrayList<String> titles, ArrayList<Integer> icons) {
        this.titles = titles;
        this.icons = icons;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SongSheetHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_open_close)
        ImageView ivOpenClose;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_song_sheet_manager)
        ImageView ivSongSheetManager;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public SongSheetHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
