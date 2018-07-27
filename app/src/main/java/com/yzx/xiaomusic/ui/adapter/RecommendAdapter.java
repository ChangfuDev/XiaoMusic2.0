package com.yzx.xiaomusic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.avos.avoscloud.AVObject;
import com.yzx.commonlibrary.utils.ResourceUtils;
import com.yzx.commonlibrary.utils.ToastUtils;
import com.yzx.xiaomusic.R;
import com.yzx.xiaomusic.model.entity.album.LatestAlbumList;
import com.yzx.xiaomusic.model.entity.songsheet.SongSheetList;
import com.yzx.xiaomusic.ui.album.AlbumDetailFragment;
import com.yzx.xiaomusic.ui.songsheet.detail.SongSheetDetailFragment;
import com.yzx.xiaomusic.ui.songsheet.list.SongSheetListFragment;
import com.yzx.xiaomusic.utils.FragmentStartUtils;
import com.yzx.xiaomusic.utils.GlideUtils;
import com.yzx.xiaomusic.utils.TimeUtils;
import com.yzx.xiaomusic.widget.SquareImageView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

import static com.yzx.xiaomusic.Constant.KEY_COVER;
import static com.yzx.xiaomusic.Constant.KEY_ID;
import static com.yzx.xiaomusic.Constant.KEY_NAME;

/**
 * @author yzx
 * @date 2018/6/23
 * Description 推荐页面Adapter
 */
public class RecommendAdapter extends DelegateAdapter.Adapter implements View.OnClickListener {

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_FOUR = 2;

    public static final int TYPE_SONG_SHEET = 3;
    public static final int TYPE_ALBUM = 4;
    public static final int TYPE_TITLE_SONG_SHEET = 5;
    public static final int TYPE_TITLE_ALBUM = 6;

    private final LayoutHelper helper;
    private final int count;
    private SupportFragment parentFragment;
    private List<SongSheetList.PlaylistsBean> playlists;
    private List<LatestAlbumList.AlbumsBean> albums;
    private List<AVObject> banners;
    boolean bannered;

    public RecommendAdapter(SupportFragment parentFragment, LayoutHelper helper, int count) {
        this.parentFragment = parentFragment;
        this.helper = helper;
        this.count = count;

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
            case TYPE_SONG_SHEET:
                return new SongSheetHolder(ResourceUtils.parseLayout(context, R.layout.item_song_sheet_list, parent));
            case TYPE_ALBUM:
                return new AlbumHolder(ResourceUtils.parseLayout(context, R.layout.item_album_list, parent));
            case TYPE_TITLE_ALBUM:
            case TYPE_TITLE_SONG_SHEET:
            default:
                return new TitleHolder(ResourceUtils.parseLayout(context, R.layout.item_title, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Context context = holder.itemView.getContext();
        switch (getItemViewType(position)) {
            case TYPE_BANNER:
                //处理下Banner重复创建导致的卡顿
                if (!bannered && banners != null) {
                    dealBanner((BannerHolder) holder);
                    bannered = !bannered;
                }
                break;
            case TYPE_FOUR:
                dealFour((FourHolder) holder);
                break;
            case TYPE_SONG_SHEET:
                if (playlists != null) {
                    SongSheetHolder songSheetHolder = (SongSheetHolder) holder;
                    SongSheetList.PlaylistsBean playlistsBean = playlists.get(position);
                    GlideUtils.loadImg(context, playlistsBean.getCoverImgUrl(), songSheetHolder.ivCover);
                    int playCount = playlistsBean.getPlayCount();
                    songSheetHolder.tvPlayCount.setText(playCount > 10000 ? String.format("%s万", String.valueOf(playCount / 10000)) : String.valueOf(playCount));
                    songSheetHolder.tvSongSheetDes.setText(playlistsBean.getName());
                    holder.itemView.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_NAME, playlistsBean.getName());
                        bundle.putString(KEY_COVER, playlistsBean.getCoverImgUrl());
                        bundle.putString(KEY_ID, String.valueOf(playlistsBean.getId()));
                        FragmentStartUtils.startFragment(parentFragment, new SongSheetDetailFragment(), bundle);
                    });
                }
                break;
            case TYPE_ALBUM:
                if (albums != null) {
                    AlbumHolder albumHolder = (AlbumHolder) holder;
                    LatestAlbumList.AlbumsBean albumsBean = albums.get(position);
                    GlideUtils.loadImg(context, albumsBean.getPicUrl(), albumHolder.ivCover);
                    albumHolder.tvTitle.setText(albumsBean.getName());
                    albumHolder.tvSubTitle.setText(albumsBean.getArtist().getName());
                    holder.itemView.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_ID, albumsBean.getId());
                        bundle.putString(KEY_NAME, albumsBean.getName());
                        bundle.putString(KEY_COVER, albumsBean.getPicUrl());
                        FragmentStartUtils.startFragment(parentFragment, new AlbumDetailFragment(), bundle);
                    });
                }
                break;
            case TYPE_TITLE_SONG_SHEET:
                TitleHolder songSheetTitleHolder = (TitleHolder) holder;
                songSheetTitleHolder.tvTitle.setText(R.string.reommendSongSheet);
                songSheetTitleHolder.itemView.setOnClickListener(v -> {
                    FragmentStartUtils.startFragment(parentFragment, new SongSheetListFragment());
                });

                break;
            case TYPE_TITLE_ALBUM:
                TitleHolder albumTitleHolder = (TitleHolder) holder;
                albumTitleHolder.tvTitle.setText(R.string.latestMusic);
                albumTitleHolder.itemView.setOnClickListener(v -> {
                    ToastUtils.showToast(R.string.latestMusic);
                });
                break;
            default:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void dealBanner(BannerHolder holder) {
        holder.banner.setDelayedTime(5000);
//        holder.banner.setIndicatorRes(R.color.colorDivider, R.color.colorAccent);
        holder.banner.setPages(banners, BannerViewHolder::new);
        holder.banner.setBannerPageClickListener((view, i) -> {
            ToastUtils.showToast(banners.get(i).getString("type"));
            holder.banner.setDelayedTime(5000);
        });
        holder.banner.start();
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
        return count;
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

    public void setBannerData(List<AVObject> banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }

//    public void setData(List<SongSheetList.PlaylistsBean> playlists, List<LatestAlbumList.AlbumsBean> albums, List<AVObject> banners) {
//        this.playlists = playlists;
//        this.albums = albums;
//        this.banners = banners;
//        notifyDataSetChanged();
//    }


    class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        MZBannerView banner;

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

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class SongSheetHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        SquareImageView ivCover;
        @BindView(R.id.tv_play_count)
        TextView tvPlayCount;
        @BindView(R.id.tv_songSheetDes)
        TextView tvSongSheetDes;

        public SongSheetHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AlbumHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        SquareImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subTitle)
        TextView tvSubTitle;

        public AlbumHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public static class BannerViewHolder implements MZViewHolder<AVObject> {
        private ImageView mImageView;
        private TextView tvTitle;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            mImageView = (ImageView) view.findViewById(R.id.iv_cover);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            return view;
        }

        @Override
        public void onBind(Context context, int position, AVObject data) {
            // 数据绑定
            tvTitle.setText(data.getString("type"));
            GlideUtils.loadImg(context, data.getString("coverUrl"), mImageView);
        }
    }
}
