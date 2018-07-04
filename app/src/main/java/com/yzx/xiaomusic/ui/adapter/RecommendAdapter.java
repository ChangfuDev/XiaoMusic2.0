package com.yzx.xiaomusic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.commonlibrary.utils.DensityUtils;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.ui.album.AlbumDetailFragment;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.ui.songsheet.list.SongSheetListFragment;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.TimeUtils;
import com.yzx.xiaomusic.widget.GridItemDecoration;
import com.yzx.xiaomusic.widget.UnScrollGridLayoutManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;

/**
 * @author yzx
 * @date 2018/6/23
 * Description 推荐页面Adapter
 */
public class RecommendAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_FOUR = 2;
    public static final int TYPE_TITLE = 3;
    private final SupportFragment parentFragment;
    private boolean hasAttach;
    private List<SongSheetList.PlaylistsBean> playlists;
    private List<LatestAlbumList.AlbumsBean> albums;

    public RecommendAdapter(SupportFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_BANNER;
            case 1:
                return TYPE_FOUR;
            case 2:
            case 4:
                return TYPE_TITLE;
            default:
                return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {
            case TYPE_BANNER:
                return new BannerHolder(ResourceUtils.parseLayout(context, R.layout.banner, parent));
            case TYPE_FOUR:
                return new FourHolder(ResourceUtils.parseLayout(context, R.layout.item_four, parent));
            case TYPE_TITLE:
                return new TitleHolder(ResourceUtils.parseLayout(context, R.layout.item_title, parent));
            default:
                return new SixHolder(ResourceUtils.parseLayout(context, R.layout.item_recyclerview, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        switch (getItemViewType(position)) {
            case TYPE_BANNER:
                dealBanner((BannerHolder) holder);
                break;
            case TYPE_FOUR:
                dealFour((FourHolder) holder);
                break;
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                if (position == 2) {
                    titleHolder.tvTitle.setText(R.string.reommendSongSheet);
                    titleHolder.llTitle.setOnClickListener(v -> {
                        FragmentStartUtils.startFragment(parentFragment, new SongSheetListFragment());
                    });
                } else if (position == 4) {
                    titleHolder.tvTitle.setText(R.string.latestMusic);
                    titleHolder.llTitle.setOnClickListener(v -> {
                        ToastUtils.showToast(R.string.latestMusic);
                    });
                }
                break;
            default:
                SixHolder sixHolder = (SixHolder) holder;
                sixHolder.recyclerView.setLayoutManager(new UnScrollGridLayoutManager(context, 2));
                sixHolder.recyclerView.addItemDecoration(new GridItemDecoration(2, DensityUtils.dip2px(context, 3), false));
                if (position == 3) {
                    SongSheetListAdapter adapter = new SongSheetListAdapter();
                    adapter.setData(playlists);
                    adapter.setOnItemClickListener((view, position1) -> {
                        SongSheetList.PlaylistsBean playlistsBean = playlists.get(position1);
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_NAME, playlistsBean.getName());
                        bundle.putString(KEY_COVER, playlistsBean.getCoverImgUrl());
                        bundle.putString(KEY_ID, String.valueOf(playlistsBean.getId()));
                        FragmentStartUtils.startFragment(parentFragment, new SongSheetDetailFragment(), bundle);
                    });
                    sixHolder.recyclerView.setAdapter(adapter);
                } else if (position == 5) {
                    AlbumListAdapter adapter = new AlbumListAdapter();
                    adapter.setData(albums);
                    sixHolder.recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener((view, position1) -> {
                        LatestAlbumList.AlbumsBean albumsBean = albums.get(position1);
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_ID, albumsBean.getId());
                        bundle.putString(KEY_NAME, albumsBean.getName());
                        bundle.putString(KEY_COVER, albumsBean.getPicUrl());
                        FragmentStartUtils.startFragment(parentFragment, new AlbumDetailFragment(), bundle);
                    });
                }
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void dealBanner(BannerHolder holder) {
        if (!hasAttach) {
            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(holder.recyclerView);
            hasAttach = true;
        }
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        BannerAdapter adapter = new BannerAdapter();
        holder.recyclerView.setAdapter(adapter);
        Observable
                .interval(3, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    holder.recyclerView.smoothScrollToPosition(aLong.intValue());
                });

    }

    private void dealFour(FourHolder holder) {
        holder.llPrivateFm.setOnClickListener(this);
        holder.llDayRecommend.setOnClickListener(this);
        holder.llSongSheet.setOnClickListener(this);
        holder.llRank.setOnClickListener(this);
        holder.tvToday.setText(TimeUtils.getToday());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_song_sheet:
                parentFragment.start(new SongSheetListFragment());
                break;
        }
    }

    public void setSongSheetData(List<SongSheetList.PlaylistsBean> playlists) {
        this.playlists = playlists;
        notifyDataSetChanged();
    }

    public void setAlbumData(List<LatestAlbumList.AlbumsBean> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FourHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_private_fm)
        LinearLayout llPrivateFm;
        @BindView(R.id.ll_day_recommend)
        LinearLayout llDayRecommend;
        @BindView(R.id.ll_song_sheet)
        LinearLayout llSongSheet;
        @BindView(R.id.ll_rank)
        LinearLayout llRank;
        @BindView(R.id.tv_today)
        TextView tvToday;

        public FourHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_title)
        LinearLayout llTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 三列六个内容
     */
    class SixHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public SixHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
